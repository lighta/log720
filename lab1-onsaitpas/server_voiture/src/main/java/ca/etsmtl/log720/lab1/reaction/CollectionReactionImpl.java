package ca.etsmtl.log720.lab1.reaction;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionReactionPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Reaction;
import ca.etsmtl.log720.lab1.ReactionHelper;
import ca.etsmtl.log720.lab1.Server_Voiture;

public class CollectionReactionImpl extends CollectionReactionPOA implements Serializable {
	private static final long serialVersionUID = 6456482369658947777L;
	private ArrayList<ReactionImpl> _list_reactions;
	
	public ArrayList<ReactionImpl> getListReactions() {
		return _list_reactions;
	}

	public CollectionReactionImpl() {
		super();
		_list_reactions = new ArrayList<ReactionImpl>();
	}
	
	public int size() {
		return _list_reactions.size();
	}

	public Reaction getReaction(int index) {
		try {
			// Trouver le reaction correspondant au parametre "index"
			ReactionImpl reaction = _list_reactions.get(index);
			// REcuperer le POA cree dans le serveur
			POA rootpoa = Server_Voiture.getPOA();
			// Activer l'objet et retourner l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(reaction);
			// Retourner une reaction
			return ReactionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet Reaction : " + e);
			return null;
		}
	}
	
	public void ajouterReaction(String reaction, int gravite) {
		ReactionImpl reac = new ReactionImpl(_list_reactions.size(),reaction,gravite);
		/*if(_list_reactions.contains(reac)){
			System.out.println("List_reaction already contains this obj \n\t\t"+reac);
			return;
		} //on autorize pour les dossiers ayant x meme reaction */
		_list_reactions.add(reac);
	}
	
	public void retirerReaction(int index){
		_list_reactions.remove(index);
	}
	
	CollectionReactionImpl trouverReactionsParDossier(Dossier myDossier){
		int tab_infractionID[] = myDossier.getListeReaction();
		CollectionReactionImpl col_reac = new CollectionReactionImpl();
		for(ReactionImpl cur_reac: _list_reactions){
			for(int id: tab_infractionID){
				if(id == cur_reac.id())
					col_reac.getListReactions().add(cur_reac);
			}
		}
		return col_reac;
	}

	public Reaction trouverReactionParId(int idReaction){
		int i=0;
		for(ReactionImpl cur_reac: _list_reactions){
			if(cur_reac.id() == idReaction)
				return getReaction(i);
			i++;
		}
		return null;
	}

	@Override
	public String toString() {
		return "CollectionReactionImpl [reactions="
				+ _list_reactions.toArray().toString() + ", size=" + _list_reactions.size() + "]";
	}
	
	
}

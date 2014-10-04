package ca.etsmtl.log720.lab1.reaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueReactionsPOA;
import ca.etsmtl.log720.lab1.CollectionReaction;
import ca.etsmtl.log720.lab1.CollectionReactionHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Reaction;
import ca.etsmtl.log720.lab1.Serialisation;
import ca.etsmtl.log720.lab1.Server_Voiture;
import ca.etsmtl.log720.lab1.Variables;

public class BanqueReactionsImpl extends BanqueReactionsPOA implements Serializable {
	private static final long serialVersionUID = 8458330526317303830L;
	CollectionReactionImpl _CollectionReactions;
	
	public BanqueReactionsImpl() {
		super();
		System.out.println("New BanqueReactionsImpl");
		this._CollectionReactions = new CollectionReactionImpl();
	}

	public CollectionReaction reactions() {
		try {
			// Recuperer le POA cree dans le serveur
			POA rootpoa = Server_Voiture.getPOA();
			// Activer l'objet et retourne l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(_CollectionReactions);
			// Retourner une Collection de reactions
			return CollectionReactionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionReaction : "	+ e);
			return null;
		}
	}

	public void ajouterReaction(String reaction, int gravite) {
		_CollectionReactions.ajouterReaction(reaction, gravite);
		saveState();
	}

	public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
		CollectionReactionImpl tmp_col_reac = _CollectionReactions.trouverReactionsParDossier(myDossier);
		if(tmp_col_reac.size()>0){
			try {
				// Recuperer le POA cree dans le serveur
				POA rootpoa = Server_Voiture.getPOA();
				// Activer l'objet et retourne l'objet CORBA
				org.omg.CORBA.Object obj = rootpoa.servant_to_reference(tmp_col_reac);
				// Retourner une Collection de reactions
				return CollectionReactionHelper.narrow(obj);
			} catch (Exception e) {
				System.out.println("Erreur retour de l'objet CollectionReaction : "	+ e);
				return null;
			}
		}
		return null;
	}

	public Reaction trouverReactionParId(int idReaction) {
		return _CollectionReactions.trouverReactionParId(idReaction);
	}

	@Override
	public String toString() {
		return "BanqueReactionImpl [reactions=" + _CollectionReactions + "]";
	}
	
	/**
	 * Enregistrer l'etat actual en persistance
	 * TODO add a queue for this would be better
	 */
	public void saveState(){
		try {
			Serialisation.encodeToFile(this, Variables.PERSISTANCE_PATH+Variables.NAME_BANK_REAC+Variables.SAVE_EXT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

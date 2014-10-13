package ca.etsmtl.log720.lab1.infraction;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionInfractionPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.InfractionHelper;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;
import ca.etsmtl.log720.lab1.Server_Poste;
import ca.etsmtl.log720.lab1.Variables;

public class CollectionInfractionImpl extends CollectionInfractionPOA implements Serializable {
	private static final long serialVersionUID = 5447497548872880000L;
	private ArrayList<InfractionImpl> _list_infractions;
	
	public ArrayList<InfractionImpl> getListInfractions() {
		return _list_infractions;
	}

	public CollectionInfractionImpl() {	
		super();
		_list_infractions = new ArrayList<InfractionImpl>();
	}

	public Infraction getInfraction(int index) {
		try {
			// Trouver le reaction correspondant au parametre "index"
			InfractionImpl infraction = _list_infractions.get(index);
			// REcuperer le POA cree dans le serveur
			POA rootpoa = Server_Poste.getPOA();
			// Activer l'objet et retourner l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(infraction);
			// Retourner une infraction
			return InfractionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet Infraction : " + e);
			return null;
		}
	}

	public int size() {
		return _list_infractions.size();
	}
	
	public void ajouterInfraction(String description, int niveau) 
			throws NiveauHorsBornesException {
		if(niveau < Variables.NIVEAU_INF_MIN || niveau > Variables.NIVEAU_INF_MAX)
			throw new NiveauHorsBornesException();
		int id = _list_infractions.size(); //by default infraction_id = new index
		InfractionImpl tmp_inf = new InfractionImpl(id, description, niveau);
		/*if(_list_infractions.contains(tmp_inf)){
			System.out.println("List_Infraction already contains this obj \n\t\t"+tmp_inf);
			return;
		}*/
		_list_infractions.add(tmp_inf);
	}
	
	public void retirerInfraction(int index){
		_list_infractions.remove(index);
	}

	public Infraction trouverInfractionParId(int idInfraction) {
		int i=0;
		for(InfractionImpl cur_infrac : _list_infractions){
			if(cur_infrac.id() == idInfraction)
				return getInfraction(i);
			i++;
		}
		return null;
	}
	
	CollectionInfractionImpl trouverInfractionsParDossier(Dossier myDossier){
		int tab_infractionID[] = myDossier.getListeInfraction();
		CollectionInfractionImpl col_inf = new CollectionInfractionImpl();
		for(InfractionImpl cur_inf: _list_infractions){
			for(int id: tab_infractionID){
				if(id == cur_inf.id())
					col_inf.getListInfractions().add(cur_inf);
			}
		}
		return col_inf;
	}

	@Override
	public String toString() {
		return "CollectionInfractionImpl [infractions="
				+ _list_infractions.toArray().toString() + ", size=" + _list_infractions.size() + "]";
	}
}

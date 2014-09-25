package ca.etsmtl.log720.lab1.infraction;

import java.util.ArrayList;
import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionInfractionPOA;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.InfractionHelper;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;
import ca.etsmtl.log720.lab1.Server_Poste;

public class CollectionInfractionImpl extends CollectionInfractionPOA {
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
		if(niveau < InfractionImpl.NIVEAU_MIN || niveau > InfractionImpl.NIVEAU_MAX)
			throw new NiveauHorsBornesException();
		int id = _list_infractions.size(); // TODO what ID do we want ??
		_list_infractions.add(new InfractionImpl(id, description, niveau));
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

	@Override
	public String toString() {
		return "CollectionInfractionImpl [infractions="
				+ _list_infractions.toArray().toString() + ", size=" + _list_infractions.size() + "]";
	}
}

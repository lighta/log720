package ca.etsmtl.log720.lab1.infraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueInfractionsPOA;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.CollectionInfractionHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;
import ca.etsmtl.log720.lab1.Serialisation;
import ca.etsmtl.log720.lab1.Server_Poste;
import ca.etsmtl.log720.lab1.Variables;

public class BanqueInfractionsImpl extends BanqueInfractionsPOA implements Serializable {
	private static final long serialVersionUID = 8579409292318180001L;
	CollectionInfractionImpl _CollectionInfractions;
	
	public BanqueInfractionsImpl() {
		super();
		System.out.println("New BanqueInfractionsImpl");
		_CollectionInfractions = new CollectionInfractionImpl();
	}

	public CollectionInfraction infractions() {
		try {
			// Recuperer le POA cree dans le serveur
			POA rootpoa = Server_Poste.getPOA();
			// Activer l'objet et retourne l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(_CollectionInfractions);
			// Retourner une Collection d'infraction
			return CollectionInfractionHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionInfraction : "	+ e);
			return null;
		}
	}

	public CollectionInfraction trouverInfractionsParDossier(Dossier mydossier) {
		CollectionInfractionImpl list_infraction = new CollectionInfractionImpl();
		int i=0;
		while(_CollectionInfractions.size() < i++){
		//	infractions.getInfraction(i)
			;  // TODO ajouter infractions qui fit dossiers
		}
		return (CollectionInfraction) list_infraction;
	}

	public Infraction trouverInfractionParId(int idInfraction) {
		return _CollectionInfractions.trouverInfractionParId(idInfraction);
	}

	public void ajouterInfraction(String description, int niveau)
			throws NiveauHorsBornesException {
		_CollectionInfractions.ajouterInfraction(description, niveau);
		saveState();
	}

	@Override
	public String toString() {
		return "BanqueInfractionImpl [infractions=" + _CollectionInfractions + "]";
	}
	
	/**
	 * Enregistrer l'etat actual en persistance
	 * TODO add a queue for this would be better
	 */
	public void saveState(){
		try {
			Serialisation.encodeToFile(this, Variables.PERSISTANCE_PATH+Variables.NAME_BANK_INF+Variables.SAVE_EXT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

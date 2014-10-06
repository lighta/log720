package ca.etsmtl.log720.lab1.dossier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.BanqueDossiersPOA;
import ca.etsmtl.log720.lab1.CollectionDossier;
import ca.etsmtl.log720.lab1.CollectionDossierHelper;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.InvalidIdException;
import ca.etsmtl.log720.lab1.NoPermisExisteDejaException;
import ca.etsmtl.log720.lab1.Serialisation;
import ca.etsmtl.log720.lab1.Server_Poste;
import ca.etsmtl.log720.lab1.Variables;

public class BanqueDossiersImpl extends BanqueDossiersPOA implements Serializable {
	private static final long serialVersionUID = 94393545026978456L;
	CollectionDossierImpl _CollectionDossiers;
	
	public BanqueDossiersImpl() {
		super();
		System.out.println("New BanqueDossiersImpl");
		_CollectionDossiers = new CollectionDossierImpl();
	}

	public CollectionDossier dossiers() {
		try {
			// Recuperer le POA cree dans le serveur
			POA rootpoa = Server_Poste.getPOA();
			// Activer l'objet et retourne l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(_CollectionDossiers);
			// Retourner une Collection de dossiers
			return CollectionDossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet CollectionDossier : "	+ e);
			return null;
		}
	}

	public CollectionDossier trouverDossiersParPlaque(String plaque) {
		CollectionDossierImpl tmp_listdos = _CollectionDossiers.matches(null,null,plaque);
		if(tmp_listdos.size()>0)
			return tmp_listdos._this();
		return null;
	}

	public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
		CollectionDossierImpl tmp_listdos = _CollectionDossiers.matches(nom,prenom,null);
		if(tmp_listdos.size()>0)
			return tmp_listdos._this();
		return null;
	}

	public Dossier trouverDossierParPermis(String noPermis) {
		return _CollectionDossiers.trouverDossierParPermis(noPermis);
	}

	public Dossier trouverDossierParId(int idDossier) {
		return _CollectionDossiers.trouverDossierParId(idDossier);
	}

	public void ajouterDossier(String nom, String prenom, String noPermis,
			String noPlaque) throws NoPermisExisteDejaException {
		_CollectionDossiers.ajouterDossier(nom, prenom, noPermis, noPlaque);
		saveState();
	}

	public void ajouterInfractionAuDossier(int idDossier, int idInfraction)
			throws InvalidIdException {
		Dossier dos_tmp = trouverDossierParId(idDossier);
		dos_tmp.ajouterInfractionAListe(idInfraction);	
		saveState();
	}

	public void ajouterReactionAuDossier(int idDossier, int idReaction)
			throws InvalidIdException {
		Dossier dos_tmp = trouverDossierParId(idDossier);
		dos_tmp.ajouterReactionAListe(idReaction);
		saveState();
	}
	
	/**
	 * Enregistrer l'etat actual en persistance
	 * TODO add a queue for this would be better
	 */
	public void saveState(){
		try {
			Serialisation.encodeToFile(this, Variables.PERSISTANCE_PATH+Variables.NAME_BANK_DOS+Variables.SAVE_EXT);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package ca.etsmtl.log720.lab1.dossier;

import ca.etsmtl.log720.lab1.BanqueDossiersPOA;
import ca.etsmtl.log720.lab1.CollectionDossier;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.InvalidIdException;
import ca.etsmtl.log720.lab1.NoPermisExisteDejaException;

public class BanqueDossierImpl extends BanqueDossiersPOA {
	CollectionDossierImpl dossiers;
	
	public BanqueDossierImpl() {
		super();
		dossiers = new CollectionDossierImpl();
	}

	public CollectionDossier dossiers() {
		return dossiers._this();
	}

	public CollectionDossier trouverDossiersParPlaque(String plaque) {
		// TODO Auto-generated method stub
		return null;
	}

	public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	public Dossier trouverDossierParPermis(String noPermis) {
		// TODO Auto-generated method stub
		return null;
	}

	public Dossier trouverDossierParId(int idDossier) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ajouterDossier(String nom, String prenom, String noPermis,
			String noPlaque) throws NoPermisExisteDejaException {
		// TODO Auto-generated method stub
		
	}

	public void ajouterInfractionAuDossier(int idDossier, int idInfraction)
			throws InvalidIdException {
		// TODO Auto-generated method stub
		
	}

	public void ajouterReactionAuDossier(int idDossier, int idReaction)
			throws InvalidIdException {
		// TODO Auto-generated method stub
		
	}

}

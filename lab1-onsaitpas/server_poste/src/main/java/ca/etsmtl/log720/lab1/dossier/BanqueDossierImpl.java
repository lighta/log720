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
		CollectionDossierImpl tmp_listdos = dossiers.matches(null,null,plaque);
		if(tmp_listdos.size()>0)
			return tmp_listdos._this();
		return null;
	}

	public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
		CollectionDossierImpl tmp_listdos = dossiers.matches(nom,prenom,null);
		if(tmp_listdos.size()>0)
			return tmp_listdos._this();
		return null;
	}

	public Dossier trouverDossierParPermis(String noPermis) {
		return dossiers.trouverDossierParPermis(noPermis);
	}

	public Dossier trouverDossierParId(int idDossier) {
		return dossiers.trouverDossierParId(idDossier);
	}

	public void ajouterDossier(String nom, String prenom, String noPermis,
			String noPlaque) throws NoPermisExisteDejaException {
		dossiers.ajouterDossier(nom, prenom, noPermis, noPlaque);		
	}

	public void ajouterInfractionAuDossier(int idDossier, int idInfraction)
			throws InvalidIdException {
		Dossier dos_tmp = trouverDossierParId(idDossier);
		dos_tmp.ajouterInfractionAListe(idInfraction);		
	}

	public void ajouterReactionAuDossier(int idDossier, int idReaction)
			throws InvalidIdException {
		Dossier dos_tmp = trouverDossierParId(idDossier);
		dos_tmp.ajouterReactionAListe(idReaction);
	}

}

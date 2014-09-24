package ca.etsmtl.log720.lab1.dossier;

import ca.etsmtl.log720.lab1.DossierPOA;

public class DossierImpl extends DossierPOA {
	int id;
	String nom;
	String noPermis;
	String noPlaque;
	String prenom;
	int niveau;
	
	public DossierImpl() {
		super();
		this.id = 0;
		this.nom = "";
		this.noPermis = "";
		this.noPlaque = "";
		this.prenom = "";
		this.niveau = 0;
	}
	
	public DossierImpl(int id, String nom, String noPermis, String noPlaque,
			String prenom, int niveau) {
		super();
		this.id = id;
		this.nom = nom;
		this.noPermis = noPermis;
		this.noPlaque = noPlaque;
		this.prenom = prenom;
		this.niveau = niveau;
	}

	
	public int id() {
		return id;
	}

	public String nom() {
		return nom;
	}

	public String noPermis() {
		return noPermis;
	}

	public String noPlaque() {
		return noPlaque;
	}

	public String prenom() {
		return prenom;
	}

	public int niveau() {
		return niveau;
	}

	public int[] getListeInfraction() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getListeReaction() {
		// TODO Auto-generated method stub
		return null;
	}

	public void ajouterReactionAListe(int idReaction) {
		// TODO Auto-generated method stub
		
	}

	public void ajouterInfractionAListe(int idInfraction) {
		// TODO Auto-generated method stub
		
	}

	public String _toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "DossierImpl [id=" + id + ", nom=" + nom + ", noPermis="
				+ noPermis + ", noPlaque=" + noPlaque + ", prenom=" + prenom
				+ ", niveau=" + niveau + "]";
	}

}

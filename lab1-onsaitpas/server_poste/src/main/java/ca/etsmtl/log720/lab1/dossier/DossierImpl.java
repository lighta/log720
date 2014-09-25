package ca.etsmtl.log720.lab1.dossier;

import ca.etsmtl.log720.lab1.DossierPOA;

public class DossierImpl extends DossierPOA {
	int id=0;
	String nom;
	String noPermis;
	String noPlaque;
	String prenom;
	int niveau=0;
	int list_infraction[]; //infractions ID
	int size_infraction=0;
	int list_reaction[]; //reaction ID
	int size_reaction=0;
	
	public DossierImpl() {
		super();
		this.nom = "";
		this.noPermis = "";
		this.noPlaque = "";
		this.prenom = "";
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
	
	public DossierImpl(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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
		return list_infraction;
	}

	public int[] getListeReaction() {
		return list_reaction;
	}

	public void ajouterReactionAListe(int idReaction) {
		list_reaction[size_reaction] = idReaction;
		size_reaction++;
		
	}

	public void ajouterInfractionAListe(int idInfraction) {
		list_infraction[size_infraction] = idInfraction;
		size_infraction++;
	}

	public String _toString() {
		return toString();
	}
	
	@Override
	public String toString() {
		return "DossierImpl [id=" + id + ", nom=" + nom + ", noPermis="
				+ noPermis + ", noPlaque=" + noPlaque + ", prenom=" + prenom
				+ ", niveau=" + niveau + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + niveau;
		result = prime * result
				+ ((noPermis == null) ? 0 : noPermis.hashCode());
		result = prime * result
				+ ((noPlaque == null) ? 0 : noPlaque.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DossierImpl other = (DossierImpl) obj;
		if (id != other.id)
			return false;
		if (niveau != other.niveau)
			return false;
		if (noPermis == null) {
			if (other.noPermis != null)
				return false;
		} else if (!noPermis.equals(other.noPermis))
			return false;
		if (noPlaque == null) {
			if (other.noPlaque != null)
				return false;
		} else if (!noPlaque.equals(other.noPlaque))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	
}

package ca.etsmtl.log720.lab1.dossier;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;

import ca.etsmtl.log720.lab1.BanqueInfractions;
import ca.etsmtl.log720.lab1.BanqueInfractionsHelper;
import ca.etsmtl.log720.lab1.DossierPOA;
import ca.etsmtl.log720.lab1.Serialisation;
import ca.etsmtl.log720.lab1.Server_Poste;
import ca.etsmtl.log720.lab1.Variables;
import ca.etsmtl.log720.lab1.infraction.BanqueInfractionsImpl;

public class DossierImpl extends DossierPOA implements Serializable {
	private static final long serialVersionUID = -6849231354131147657L;
	int id=0;
	String nom;
	String noPermis;
	String noPlaque;
	String prenom;
	int niveau=0;
	private ArrayList<Integer> list_infraction; //infractions ID
	private ArrayList<Integer> list_reaction; //reaction ID
	
	public DossierImpl(int id, String nom, String noPermis, String noPlaque,
			String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.noPermis = noPermis;
		this.noPlaque = noPlaque;
		this.prenom = prenom;
		this.niveau = 0; //by default niveau=0, sera maj lors d'une infraction
		list_infraction = new ArrayList<Integer>(); //infractions ID
		list_reaction = new ArrayList<Integer>(); //reaction ID
	}
	
	public DossierImpl(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		list_infraction = new ArrayList<Integer>(); //infractions ID
		list_reaction = new ArrayList<Integer>(); //reaction ID
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

	private int[] ArrayListToInt(ArrayList<Integer> al){ //not that pretty but to avoid lib dependancy
		int tmp[] = new int[al.size()], i=0;
		for(int cur : al){
			tmp[i] = cur;
			i++;
		}
		return tmp;
	}
	
	public int[] getListeInfraction() {
		return ArrayListToInt(list_infraction);
	}

	public int[] getListeReaction() {
		return ArrayListToInt(list_reaction);
	}

	public void ajouterReactionAListe(int idReaction) {
		System.out.println("Adding reaction id="+idReaction+" to dossier");
		list_reaction.add(idReaction);
	}
	
	private void updateNiveau(int idInfraction){
		BanqueInfractions banqueInfractions;
		try  { //then if not found try by orb
			NameComponent[] name_inf = new NameComponent[] { new NameComponent(Variables.NAME_BANK_INF, "service") };
			NamingContextExt nc = Server_Poste.getNC();
			banqueInfractions = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));		
		}
		catch (Exception e) {
			System.out.println("Couldn't fetch banqueinfraction so couldn't update niveau dossier");
			return;	
		}
		
		int niveauInfraction =  banqueInfractions.trouverInfractionParId(idInfraction).niveau();	
		if(this.niveau < niveauInfraction){
			this.niveau = niveauInfraction;
		}
	}

	public void ajouterInfractionAListe(int idInfraction) {
		System.out.println("Adding infraction id="+idInfraction+" to dossier");
		list_infraction.add(idInfraction);
		updateNiveau(idInfraction);
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

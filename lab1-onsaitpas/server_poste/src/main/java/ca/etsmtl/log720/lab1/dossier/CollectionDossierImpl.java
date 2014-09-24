package ca.etsmtl.log720.lab1.dossier;

import ca.etsmtl.log720.lab1.CollectionDossierPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.NoPermisExisteDejaException;

public class CollectionDossierImpl extends CollectionDossierPOA {
	private DossierImpl dossiers[];
	private int size;
	
	public CollectionDossierImpl() {
		super();
		size = 0;
	}

	public Dossier getDossier(int index) {
		if(size < index)
			return null;
		return dossiers[index]._this();
	}

	public int size() {
		return size;
	}
	
	public void ajouterDossier(String nom, String prenom, String noPermis,
			String noPlaque)  throws NoPermisExisteDejaException {
		// TODO niveau ??
		int niveau=0;
		DossierImpl tmp_dos = new DossierImpl(size, nom, noPermis, noPlaque, prenom, niveau);
		int i=0;
		while(size>i++){
			if(dossiers[i].equals(tmp_dos)) //deja present
					throw new NoPermisExisteDejaException();
		}	
		dossiers[size] = tmp_dos;
		size++;
	}
	
	public void ajouterDossier(DossierImpl dossier)  throws NoPermisExisteDejaException {
		int i=0;
		while(size>i++){
			if(dossiers[i].equals(dossier)) //deja present
					throw new NoPermisExisteDejaException();
		}	
		dossiers[size] = dossier;
		size++;
	}
	
	public void retirerDossier(int index){
		if(index==0) //nothing to do
			return;
		int i= index;
		while(i < size-1){
			dossiers[i] = dossiers[i+1];
			i++;
		}
		dossiers[size] = null; //the last one is now 1 index below
		size--;
	}
	
	public Dossier trouverDossierParId(int idDossier) {
		int i=0;
		while(size > i++){
			if(dossiers[i].id == idDossier)
				return getDossier(i);
		}
		return null;
	}
	
	public Dossier trouverDossierParPermis(String permis) {
		int i=0;
		while(size > i++){
			if(dossiers[i].noPermis == permis)
				return getDossier(i);
		}
		return null;
	}
	
	public CollectionDossierImpl matches(String nom, String prenom, String noPlaque){
		CollectionDossierImpl tmp_collecdos = new CollectionDossierImpl();
		int i=0;
		
		while(size > i++){
			boolean match=false;
			DossierImpl cur = dossiers[i];
			if(cur==null) //shoudln't ever happen
				continue;
			
			if (noPlaque != null) { //match by plaque
				if (cur.noPlaque != null && noPlaque.equals(cur.noPlaque)){
					match=true;
				}
				else continue;
			}
			if (nom != null) { //match by name
				if (cur.nom != null && nom.equals(cur.nom)){
					match=true;
				}
				else continue;
			}
			if (prenom != null) { //match by 1st name
				if (cur.prenom != null && prenom.equals(cur.prenom)){
					match=true;
				}
				else continue;
			}
			
			if(match){
				try {
					tmp_collecdos.ajouterDossier(cur);
				} catch (NoPermisExisteDejaException e) { //already there but shouldn't happen
					e.printStackTrace();
				}
			}
		}
		return tmp_collecdos;
	}
}

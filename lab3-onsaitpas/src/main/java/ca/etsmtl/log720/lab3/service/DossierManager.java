package ca.etsmtl.log720.lab3.service;

import java.util.List;

import ca.etsmtl.log720.lab3.domain.Dossier;
import ca.etsmtl.log720.lab3.repository.DossierDao;

public class DossierManager {
    // private List<Dossier> dossiers;
    private DossierDao dossierDao;

    public List<Dossier> getDossiers() {
        // return dossiers;
        return dossierDao.getDossierList();
    }

    public void setDossierDao(DossierDao dossierDao) {
        this.dossierDao = dossierDao;
    }

    public void addDossier(Dossier dossier) {
    	dossierDao.insert(dossier);
	}
    
    public Dossier searchDossierByID(int dosId){
    	for(Dossier cur_dos : getDossiers() ){
			if(cur_dos.getId()==dosId){
				return cur_dos;
			}
		}
    	return null;
    }
    
    public boolean ajouterDossier(String nom, String prenom, String nopermis, String noplaque){
		Dossier dos = new Dossier(nom, prenom, nopermis, noplaque);
		addDossier(dos);
		return true;
	}
}
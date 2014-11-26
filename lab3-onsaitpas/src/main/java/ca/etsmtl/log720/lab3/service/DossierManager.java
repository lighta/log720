package ca.etsmtl.log720.lab3.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

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

    public boolean addDossier(Dossier dossier) {
    	try {
    		dossierDao.insert(dossier);
		} catch (ConstraintViolationException e) {
			return false;
		}
    	return true;
	}
    
    /**
     * Verifie si le noPermis n'est pas deja enregistrer
     * @param noPermis : new noPermis
     * @return true:deja present, false=non present
     */
    public boolean chkDuplicate_NoPermis(String noPermis){
    	for(Dossier cur_dos : getDossiers() ){
			if(noPermis.equalsIgnoreCase(cur_dos.getNopermis()) ){
				return true;
			}
		}
    	return false;
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
    	if(chkDuplicate_NoPermis(nopermis)==true) return false;
    	
		Dossier dos = new Dossier(nom, prenom, nopermis, noplaque);
		return addDossier(dos);
	}
}
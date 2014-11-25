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
}
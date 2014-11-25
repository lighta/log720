package ca.etsmtl.log720.lab3.service;

import java.util.List;

import ca.etsmtl.log720.lab3.domain.Dosinfraction;
import ca.etsmtl.log720.lab3.repository.DosinfractionDao;

public class DossierInfManager {
    // private List<Dossier> dossiers;
    private DosinfractionDao dosinfractionDao;

    public List<Dosinfraction> getDossierInf() {
        // return dossiers;
        return dosinfractionDao.getDossierInfractionsList();
    }

    public void setDosinfractionDao(DosinfractionDao dosinfractionDao) {
        this.dosinfractionDao = dosinfractionDao;
    }

    public void addDossierInf(Dosinfraction dossierinf) {
    	dosinfractionDao.insert(dossierinf);
	}
}
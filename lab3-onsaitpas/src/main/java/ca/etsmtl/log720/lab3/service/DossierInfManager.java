package ca.etsmtl.log720.lab3.service;

import java.util.Date;
import java.util.List;

import ca.etsmtl.log720.lab3.domain.Dosinfraction;
import ca.etsmtl.log720.lab3.domain.DosinfractionId;
import ca.etsmtl.log720.lab3.domain.Dossier;
import ca.etsmtl.log720.lab3.domain.Infraction;
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
    
    public boolean ajouteInfractionADossier(Dossier dos, Infraction inf){
		DosinfractionId dosInfId = new DosinfractionId(dos.getId(),inf.getId(),new Date());
		Dosinfraction dosInf = new Dosinfraction(dosInfId,dos,inf);
		addDossierInf(dosInf); //save new relation in persistance
		return true; 
	}
}
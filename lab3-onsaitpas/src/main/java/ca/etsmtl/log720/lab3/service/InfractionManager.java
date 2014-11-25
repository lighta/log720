package ca.etsmtl.log720.lab3.service;

import java.util.List;

import ca.etsmtl.log720.lab3.domain.Infraction;
import ca.etsmtl.log720.lab3.repository.InfractionDao;

public class InfractionManager {
    // private List<Infraction> infractions;
    private InfractionDao infractionDao;

    public List<Infraction> getInfractions() {
        // return infractions;
        return infractionDao.getInfractionList();
    }

    public void setInfractionDao(InfractionDao infractionDao) {
        this.infractionDao = infractionDao;
    }
 
    public void addInfraction(Infraction infraction) {
    	infractionDao.insert(infraction);
	}
}
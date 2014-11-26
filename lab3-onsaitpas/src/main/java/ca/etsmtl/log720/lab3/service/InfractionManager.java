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
    
    public Infraction searchInfractionByID(int infId){
    	for(Infraction cur_inf : getInfractions() ){
			if(cur_inf.getId()==infId){
				return cur_inf;
			}
		}
    	return null;
    }
    
	public boolean ajouterInfraction(String description, int gravite){
		if(chk_gravite(gravite)==false) return false;	
		Infraction inf = new Infraction(description,gravite);
		addInfraction(inf);
		return true;
	}
	
    
    public boolean chk_gravite(int gravite) {
		 return gravite < 1 ? false : gravite > 10 ? false : true;
	 }
}
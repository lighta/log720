package ca.etsmtl.log720.lab3.web;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ca.etsmtl.log720.lab3.domain.Dosinfraction;
import ca.etsmtl.log720.lab3.domain.Dossier;
import ca.etsmtl.log720.lab3.domain.Infraction;
import ca.etsmtl.log720.lab3.service.DossierInfManager;
import ca.etsmtl.log720.lab3.service.InfractionManager;
import ca.etsmtl.log720.lab3.service.DossierManager;


@Controller
public class ViewController {
    protected final Log    logger = LogFactory.getLog(getClass());
    @Autowired
    private InfractionManager infractionManager;
    @Autowired
    private DossierManager dossierManager;
    @Autowired
    private DossierInfManager dossierInfManager;
	
//	@RequestMapping(value="/")
//	public ModelAndView home() {
//		List<Infraction> infractions = this.infractionManager.getInfractions();
//		List<Dossier> dossiers = this.dossierManager.getDossiers();
//		List<Dosinfraction> dosInfs = this.dossierInfManager.getDossierInf();
//		ModelAndView model = new ModelAndView("home");
//		model.addObject("infractions", infractions);
//		model.addObject("dossiers", dossiers);
//		model.addObject("dosInfs", dosInfs);
//		return model;
//	}
	
	@RequestMapping(value="/")
	public ModelAndView home() {	
		List<Infraction> infractions = this.infractionManager.getInfractions();
		List<Dossier> dossiers = this.dossierManager.getDossiers();
		ModelAndView model = new ModelAndView("MainView");
		model.addObject("infractions", infractions);
		model.addObject("dossiers", dossiers);
		return model;
	}
	
	@RequestMapping(value="/addDos", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("prenom") String prenom, @RequestParam("nom") String nom,
			@RequestParam("permis") String nopermis, @RequestParam("plaque") String noplaque,
			HttpServletRequest request) {
		if(request.isUserInRole("log720_Admin")){
			this.dossierManager.ajouterDossier(nom, prenom, nopermis, noplaque);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/addInf", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("description") String description, @RequestParam("gravite") int niveau, HttpServletRequest request) {
		if(request.isUserInRole("log720_Admin")){
			this.infractionManager.ajouterInfraction(description,niveau);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/addinfToDos", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("selectedDos") int iddos, @RequestParam("selectedInf") int idinf, HttpServletRequest request) {
		if(request.isUserInRole("log720_Policier")){
			ajouteInfractionADossier(iddos,idinf);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/viewdos", method = RequestMethod.GET)
	public ModelAndView viewDos(@RequestParam("selectedDos") int iddos) {
		ModelAndView model = new ModelAndView("viewdos");
		Dossier dossier = this.dossierManager.searchDossierByID(iddos);; //test see 1st dos
		if(dossier != null){
			model.addObject("dossier", dossier);
			Set<Dosinfraction> dosInfs = dossier.getDosinfractions();
			model.addObject("dosInfs", dosInfs);		
		}
		return model;
	}
	
	
	
	private boolean ajouteInfractionADossier(int dosId, int infId){
		Dossier dos= this.dossierManager.searchDossierByID(dosId); //recherche du dossier existant byID
		if(dos==null) return false;
		Infraction inf=this.infractionManager.searchInfractionByID(infId);
		if(inf==null) return false;
		this.dossierInfManager.ajouteInfractionADossier(dos,inf); //save new relation in persistance
		return true; 
	}
	
    public void setInfractionManager(InfractionManager InfractionManager) {
        this.infractionManager = InfractionManager;
    }
    
    public void setDossierManager(DossierManager DossierManager) {
        this.dossierManager = DossierManager;
    }
    
    public void setDossierInfManager(DossierInfManager DossierInfManager) {
        this.dossierInfManager = DossierInfManager;
    }
}
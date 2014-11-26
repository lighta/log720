package ca.etsmtl.log720.lab3.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ca.etsmtl.log720.lab3.domain.Dosinfraction;
import ca.etsmtl.log720.lab3.domain.DosinfractionId;
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
	public ModelAndView home(HttpServletRequest request) {
		if (request.getAttribute("logoff") != null) {
			System.out.println("Asking tologoff ");
//		    session.invalidate();
//		    response.sendRedirect("/lab3/");
//		    return;
		}
		
		System.out.println("Defaut display ");
		List<Infraction> infractions = this.infractionManager.getInfractions();
		List<Dossier> dossiers = this.dossierManager.getDossiers();
		ModelAndView model = new ModelAndView("MainView");
		model.addObject("infractions", infractions);
		model.addObject("dossiers", dossiers);
		return model;
	}
	
	@RequestMapping(value="/viewdossier")
	public ModelAndView viewDos() {
		List<Dosinfraction> dosInfs = this.dossierInfManager.getDossierInf();
		Dossier dossier = this.dossierManager.getDossiers().get(0); //test see 1st dos
		ModelAndView model = new ModelAndView("viewdos");
		model.addObject("dosInfs", dosInfs);
		model.addObject("dossier", dossier);
		return model;
	}
    
	public boolean ajouteInfraction(int dosId, int infId){
		Dossier dos=null; //recherche du dossier existant byID
		for(Dossier cur_dos : this.dossierManager.getDossiers() ){
			if(cur_dos.getId()==dosId){
				dos=cur_dos;
				break;
			}
		}
		if(dos==null) return false;
		
		Infraction inf=null;
		for(Infraction cur_inf : this.infractionManager.getInfractions() ){
			if(cur_inf.getId()==infId){
				inf=cur_inf;
				break;
			}
		}
		if(inf==null) return false;
		
		DosinfractionId dosInfId = new DosinfractionId(dos.getId(),inf.getId(),new Date());
		Dosinfraction dosInf = new Dosinfraction(dosInfId,dos,inf);
		dos.getDosinfractions().add(dosInf);
		inf.getDosinfractions().add(dosInf);
		dos.calcNiveau(); //maj du niveau
		//todo try catch error
		this.dossierInfManager.addDossierInf(dosInf); //save new relation in persistance
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
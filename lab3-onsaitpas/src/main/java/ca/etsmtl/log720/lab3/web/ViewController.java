package ca.etsmtl.log720.lab3.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(value="/")
	public ModelAndView home() {
		List<Infraction> infractions = this.infractionManager.getInfractions();
		List<Dossier> dossiers = this.dossierManager.getDossiers();
		List<Dosinfraction> dosInfs = this.dossierInfManager.getDossierInf();
		ModelAndView model = new ModelAndView("home");
		model.addObject("infractions", infractions);
		model.addObject("dossiers", dossiers);
		model.addObject("dosInfs", dosInfs);
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
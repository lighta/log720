package ca.etsmtl.log720.lab3.web;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import ca.etsmtl.log720.lab3.utils.HTMLFilter;


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
		ModelAndView model = new ModelAndView("MainView");
		model.addObject("infractions", infractions);
		model.addObject("dossiers", dossiers);
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("logoff") boolean logoff, HttpSession session) {
		if(logoff==true){
			session.invalidate();
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/addDos", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("prenom") String prenom, @RequestParam("nom") String nom,
			@RequestParam("permis") String nopermis, @RequestParam("plaque") String noplaque,
			HttpServletRequest request) {
		String reason = null;
		if(request.isUserInRole("log720_Admin")){
			String filter_nom = HTMLFilter.filter(nom);
			String filter_prenom = HTMLFilter.filter(prenom);
			String filter_nopermis = HTMLFilter.filter(nopermis);
			String filter_noplaque = HTMLFilter.filter(noplaque);
			if(this.dossierManager.ajouterDossier(filter_nom, filter_prenom, filter_nopermis, filter_noplaque)==false)
				reason = "Duplicated KEY for permis";
			else reason = "Dossier Added with success";
		}
		else reason = "BAD USER role";
		ModelAndView model = new ModelAndView("redirect:/");
		model.addObject("dos_addFail_reason",reason);
		return model;
	}
	
	@RequestMapping(value="/addInf", method = RequestMethod.GET)
	public ModelAndView addinf(@RequestParam("description") String description, @RequestParam("gravite") String niveau, HttpServletRequest request) {
		String reason = null;
		if(request.isUserInRole("log720_Admin")){
			String filter_desc = HTMLFilter.filter(description);
			String filter_niveau = HTMLFilter.filter(niveau);
			
			if(this.infractionManager.chk_is_int(filter_niveau) == false){
				reason = "Gravity number must be numeric";
			}
			else
			{
				int niveau_int = Integer.parseInt(filter_niveau);
				if(this.infractionManager.ajouterInfraction(filter_desc,niveau_int)==false){
					reason = "Invalide niveau de gravite";
				}
			}
		} else reason = "BAD USER role";
		
		ModelAndView model = new ModelAndView("redirect:/");
		model.addObject("inf_addFail_reason",reason);
		return model;
	}
	
	@RequestMapping(value="/addinfToDos", method = RequestMethod.GET)
	public ModelAndView addinftodos(@RequestParam("selectedDos") String iddos, @RequestParam("selectedInf") String idinf, HttpServletRequest request) {
		String reason = null;
		if(request.isUserInRole("log720_Policier")){
			String filter_iddos = HTMLFilter.filter(iddos);
			String filter_idinf = HTMLFilter.filter(idinf);
			
			if(this.infractionManager.chk_is_int(filter_iddos) == false || this.infractionManager.chk_is_int(filter_idinf) == false){
				reason = "Le numero de dossier et le niveau d infraction doivent etre des nombres";
			}
			else
			{
				int iddos_int = Integer.parseInt(filter_iddos);
				int idinf_int = Integer.parseInt(filter_idinf);
			
				if(ajouteInfractionADossier(iddos_int,idinf_int)==false){
					reason = "Error adding infraction to dossier";
				}
			}
		} else reason = "BAD USER role";
		return home();
	}
	
	@RequestMapping(value="/viewdos", method = RequestMethod.GET)
	public ModelAndView viewDos(@RequestParam("selectedDos") int iddos) {
		//TODO try with iddos = not int
		ModelAndView model = new ModelAndView("viewdos");
		Dossier dossier = this.dossierManager.searchDossierByID(iddos);; //test see 1st dos
		if(dossier != null){
			model.addObject("dossier", dossier);
			Set<Dosinfraction> dosInfs = dossier.getDosinfractions();
			model.addObject("dosInfs", dosInfs);		
		}
		return model;
	}
	
	@ExceptionHandler(org.springframework.transaction.CannotCreateTransactionException.class)
	public ModelAndView handleHibernateException(org.springframework.transaction.CannotCreateTransactionException ex) {
		ModelAndView model = new ModelAndView("error/HibernateSession");
		model.addObject("exception", ex);
		return model;
	}
	
	@ExceptionHandler(java.net.UnknownHostException.class)
	public ModelAndView handleHostException(java.net.UnknownHostException ex) {
		ModelAndView model = new ModelAndView("error/UnknownHost");
		model.addObject("exception", ex);
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
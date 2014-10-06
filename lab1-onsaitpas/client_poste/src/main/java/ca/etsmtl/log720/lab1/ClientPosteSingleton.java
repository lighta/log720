package ca.etsmtl.log720.lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import ca.etsmtl.log720.lab1.view.InterfacePoste;

public class ClientPosteSingleton implements ActionListener {
		String[] args;
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
		InterfacePoste view;
		
		//action listener commandmap for listener
		public final static int ADD_INFRACTION = 100;
		public final static int ADD_DOSSIER = 101;
		
	
		private ClientPosteSingleton() { }
	 
		private static class SingletonHolder { 
			private static final ClientPosteSingleton INSTANCE = new ClientPosteSingleton();
		}
	 
		public static ClientPosteSingleton getInstance() {
			return SingletonHolder.INSTANCE;
		}
		
		
		// Setters
		public void setArgs(String[] arguments)
		{
			args = arguments;
		}
		
		public void setView(InterfacePoste iView)
		{
			view = iView;
		}
		
		public void setBanqueInfractions(BanqueInfractions banqueInfractions)
		{
			banque_infraction = banqueInfractions;
		}
		
		public void setBanqueDossiers(BanqueDossiers banqueDossiers)
		{
			banque_dossier = banqueDossiers;
		}
		
		
		// Getters
		public String[] getArgs()
		{
			return args;
		}
		
		public InterfacePoste getView()
		{
			return view;
		}
		
		public BanqueDossiers getBanqueDossiers()
		{
			return banque_dossier;
		}
		
		public BanqueInfractions getBanqueInfractions()
		{
			return banque_infraction;
		}

		
		private void refreshListInf(){
			CollectionInfraction collec_inf = banque_infraction.infractions(); //avoid refetch
			view.infractionsPosteView.refresh(collec_inf);
		}
		
		private void refreshListDos(){
			CollectionDossier collec_dos = banque_dossier.dossiers(); //avoid refetch
			view.dossiersView.refresh(collec_dos);
		}
		
		public void refresh(){
			refreshListInf();
			refreshListDos();
		}
		
		void ajoutDossier(String prenom, String nom, String plaque, String permis) {
			BanqueDossiers banque_dossier = ClientPosteSingleton.getInstance().getBanqueDossiers();		
			try {
				banque_dossier.ajouterDossier(prenom, nom, plaque, permis);	
			} catch (NoPermisExisteDejaException e) {
				// TODO put this graphical
				System.out.println("Ajout de dossier impossible, le num de permis existe deja"); 
			}
		}
		
		void ajoutInfraction(String description) {		
			try {
				// TODO, currently simulator, put this at IntTextField
				// Random de 1 a 5 pour la gravite
				Random rand = new Random();
				int gravite = rand.nextInt((5 - 1) + 1) + 1;	
				banque_infraction.ajouterInfraction(description, gravite);	
			} catch (NiveauHorsBornesException e) {
				System.out.println("Ajout d'infraction impossible, le niveau est hors borne"); 
			}
		}


		//controller of view (when need to talk to orb stuff)
		public void actionPerformed(ActionEvent arg0) {
			final String action = arg0.getActionCommand();
			switch(Integer.parseInt(action)){ //just to support older jdk
				case ADD_INFRACTION : {
					String description = view.infractionsPosteView.getDescription();
					ajoutInfraction(description);
					refreshListInf();
					break;
				}
				case ADD_DOSSIER : {
					String prenom 	= view.dossiersView.getPrenom();
	        	    String nom 		= view.dossiersView.getNom();
	        	    String plaque 	= view.dossiersView.getPlaque();
	        	    String permis 	= view.dossiersView.getPermis();
					ajoutDossier(prenom, nom, plaque, permis);
					refreshListDos();
					break;	
				}
			}
		}
}

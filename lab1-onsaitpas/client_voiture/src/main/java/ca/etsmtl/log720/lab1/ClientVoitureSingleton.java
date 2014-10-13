package ca.etsmtl.log720.lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import ca.etsmtl.log720.lab1.view.InterfaceVoiture;

public class ClientVoitureSingleton implements ActionListener {
		private String[] args;
		private BanqueReactions banque_reaction;
		private BanqueInfractions banque_infraction;
		private BanqueDossiers banque_dossier;
		private InterfaceVoiture view;
		private Dossier currentDossier;
		
		//action listener commandmap for listener
		// [100-199] is reserved for ClientPosteSingleton
		public final static int ADD_REACTION_LIST = 200;
		public final static int ADD_REACTION_TO_DOS = 201;
		public final static int ADD_INFRACTION_TO_DOS = 210;
		public final static int SEARCH_DOS = 220;
		public final static int SELECT_DOS = 221;
		
	
		private ClientVoitureSingleton() { 
			// Launch interface
			view = new InterfaceVoiture(this);
			view.setVisible(true);
		}
	 
		private static class SingletonHolder { 
			private static final ClientVoitureSingleton INSTANCE = new ClientVoitureSingleton();
		}
	 
		public static ClientVoitureSingleton getInstance() {
			return SingletonHolder.INSTANCE;
		}


		public void setBanque_reaction(BanqueReactions banque_reaction) {
			this.banque_reaction = banque_reaction;
		}
		public void setBanque_infraction(BanqueInfractions banque_infraction) {
			this.banque_infraction = banque_infraction;
		}
		public void setBanque_dossier(BanqueDossiers banque_dossier) {
			this.banque_dossier = banque_dossier;
		}

		
		// Setters
		public void setArgs(String[] arguments)
		{
			args = arguments;
		}
		
		
		// Getters
		public String[] getArgs()
		{
			return args;
		}
		
		public InterfaceVoiture getView()
		{
			return view;
		}
		
		public BanqueDossiers getBanqueDossiers()
		{
			return banque_dossier;
		}

		
		private void refreshListInf(){
			CollectionInfraction collec_inf = banque_infraction.infractions(); //avoid refetch
			view.infractionsView.refresh(collec_inf);
		}
		
		private void refreshListReac(){
			CollectionReaction collec_reac = banque_reaction.reactions(); //avoid refetch
			view.reactionView.refresh(collec_reac);
		}
		
		public void refresh(){
			refreshListInf();
			refreshListReac();
		}	
		
		private void add_reaction_to_list(String description, int gravite) {	
			try {	
				banque_reaction.ajouterReaction(description, gravite);	
			} catch (Exception e) {
				// TODO put this graphical
				String message = "Ajout d'infraction impossible, le niveau est hors borne";
				view.reactionView.showCustomMessage(message);
			}
		}
		private void add_reaction_to_dos(String currentReaction) {	
			System.out.println("Setting new infraction: "+currentReaction);
			
			BanqueDossiers banque_dossier = ClientVoitureSingleton.getInstance().getBanqueDossiers();		
			try {
				System.out.println("CURRENT DOSSIER:"+currentDossier._toString());
				// Search the string to retrieve the infraction ID
				String[] selectedReactionArr = currentReaction.split(",");
				
				Scanner in = new Scanner(selectedReactionArr[0]).useDelimiter("[^0-9]+");
				int idReaction = in.nextInt();
				
				// Assign the currently selected dossier
				currentDossier.ajouterReactionAListe(idReaction);
				
				// Display the selected dossier on main menu
				view.showCurrentDossier(currentDossier._toString());
				
				// Return to main menu
				view.reactionView.dispose();
					
			} catch (NullPointerException npe) {
				String message = "Impossible d'ajouter une reaction \n\n Veuillez vous assurer de selectionner une reaction \n Veuillez vous assurer d'avoir un dossier actif";
				view.infractionsView.showCustomMessage(message);
			}
		}
		private void add_infraction_to_dos(String currentInfraction) {	
			System.out.println("Setting new infraction: "+currentInfraction);
			
			BanqueDossiers banque_dossier = ClientVoitureSingleton.getInstance().getBanqueDossiers();		
			try {
				System.out.println("CURRENT DOSSIER:"+currentDossier._toString());
				// Search the string to retrieve the infraction ID
				String[] selectedInfractionArr = currentInfraction.split(",");
				
				Scanner in = new Scanner(selectedInfractionArr[0]).useDelimiter("[^0-9]+");
				int idInfraction = in.nextInt();
				
				// Assign the currently selected dossier
				currentDossier.ajouterInfractionAListe(idInfraction);
				
				// Display the selected dossier on main menu
				view.showCurrentDossier(currentDossier._toString());
				
				// Return to main menu
				view.infractionsView.dispose();
					
			} catch (NullPointerException npe) {
				String message = "Impossible d'ajouter une infraction \n\n Veuillez vous assurer de selectionner une infraction \n Veuillez vous assurer d'avoir un dossier actif";
				view.infractionsView.showCustomMessage(message);
			}
			
		}
		
		private void search_dos(String prenom, String nom, String plaque, String permis) 
		{
			// Full search (empty fields)
			if ( prenom.isEmpty() && nom.isEmpty() && plaque.isEmpty() && permis.isEmpty() )
			{
				BanqueDossiers banque_dossier = ClientVoitureSingleton.getInstance().getBanqueDossiers();
				CollectionDossier collec_dos = banque_dossier.dossiers(); //avoid refetch
				
				// No result found handling
				if ( collec_dos.size() > 1 )
				{
					view.rechercheView.refresh(collec_dos);
				}
				else
				{
					String message = "Aucun resultat trouve!";
					view.rechercheView.showCustomMessage(message);
				}
			}
			
			// Search by nom/prenom
			else if ( plaque.isEmpty() && permis.isEmpty() )
			{
				// No result found catching
				try{
					CollectionDossier searched_dossier = banque_dossier.trouverDossiersParNom(nom, prenom);
					view.rechercheView.refresh(searched_dossier);
				}catch (NullPointerException npe){
					String message = "Aucun resultat trouve!";
					view.rechercheView.showCustomMessage(message);
				}
			}
			
			// Search by plaque
			else if ( prenom.isEmpty() && nom.isEmpty() && !plaque.isEmpty() && permis.isEmpty() )
			{
				// No result found catching
				try{
					CollectionDossier searched_dossier = banque_dossier.trouverDossiersParPlaque(plaque);
					view.rechercheView.refresh(searched_dossier);
				}catch (NullPointerException npe){
					String message = "Aucun resultat trouve!";
					view.rechercheView.showCustomMessage(message);
				}
			}
			
			// Search by permis
			else if (prenom.isEmpty() && nom.isEmpty() && plaque.isEmpty() && !permis.isEmpty() )
			{
				// No result found catching
				try{
					Dossier searched_dossier = banque_dossier.trouverDossierParPermis(permis);
					view.rechercheView.refreshSingle(searched_dossier);
				}catch (NullPointerException npe){
					String message = "Aucun resultat trouve!";
					view.rechercheView.showCustomMessage(message);
				}
			}
		}
		private void select_dos() {	
			String selectedDossierString = view.rechercheView.getSelectedDossier();
			
			// Search the string to retrieve the ID
			String[] selectedDossierArr = selectedDossierString.split(",");
			Scanner in = new Scanner(selectedDossierArr[0]).useDelimiter("[^0-9]+");
			int selectedDossierID = in.nextInt();
			
			// Assign the currently selected dossier and return to main menu
			currentDossier = banque_dossier.trouverDossierParId(selectedDossierID);
			view.rechercheView.dispose();
			
			// Display the selected dossier on main menu
			view.showCurrentDossier(currentDossier._toString());
		}


		//controller of view (when need to talk to orb stuff)
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println(this.getClass().getName()+" actionPerformed with arg0="+arg0);
			final String action = arg0.getActionCommand();
			switch(Integer.parseInt(action)){ //just to support older jdk
				case ADD_REACTION_LIST :{
					System.out.println("Executing ADD_REACTION_LIST");
					String description = view.reactionView.getDescription();
					int gravite = view.reactionView.getGravite();
					add_reaction_to_list(description,gravite);
					refreshListReac();
					break;
				}
				case ADD_REACTION_TO_DOS :{
					System.out.println("Executing ADD_REACTION_TO_DOS");
					String currentReaction = view.reactionView.getCurrentReaction();
					add_reaction_to_dos(currentReaction);
					break;
				}
				case ADD_INFRACTION_TO_DOS :{
					System.out.println("Executing ADD_INFRACTION_TO_DOS");
					String currentInfraction = view.infractionsView.getCurrentInfraction();
					add_infraction_to_dos(currentInfraction);
					break;
				}
				case SEARCH_DOS :{
					System.out.println("Executing SEARCH_DOS");
					String prenom 	= view.rechercheView.getPrenom();
	        	    String nom 		= view.rechercheView.getNom();
	        	    String plaque 	= view.rechercheView.getPlaque();
	        	    String permis 	= view.rechercheView.getPermis();
					search_dos(prenom, nom, plaque, permis);
					break;
				}
				case SELECT_DOS :{
					System.out.println("Executing SELECT_DOS");
					select_dos();
					break;
				}
			}
		}
}

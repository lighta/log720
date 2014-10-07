package ca.etsmtl.log720.lab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import ca.etsmtl.log720.lab1.view.InterfaceVoiture;

public class ClientVoitureSingleton implements ActionListener {
		private String[] args;
		private BanqueReactions banque_reaction;
		private BanqueInfractions banque_infraction;
		private BanqueDossiers banque_dossier;
		private InterfaceVoiture view;
		
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
		
		private void add_reaction_to_list() {	
			;
		}
		private void add_reaction_to_dos() {	
			;
		}
		private void add_infraction_to_dos() {	
			;
		}
		private void search_dos() {	
			;
		}
		private void select_dos() {	
			;
		}


		//controller of view (when need to talk to orb stuff)
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println(this.getClass().getName()+" actionPerformed with arg0="+arg0);
			final String action = arg0.getActionCommand();
			switch(Integer.parseInt(action)){ //just to support older jdk
				case ADD_REACTION_LIST :{
					System.out.println("Executing ADD_REACTION_LIST");
					add_reaction_to_list();
					break;
				}
				case ADD_REACTION_TO_DOS :{
					System.out.println("Executing ADD_REACTION_TO_DOS");
					add_reaction_to_dos();
					break;
				}
				case ADD_INFRACTION_TO_DOS :{
					System.out.println("Executing ADD_INFRACTION_TO_DOS");
					add_infraction_to_dos();
					break;
				}
				case SEARCH_DOS :{
					System.out.println("Executing SEARCH_DOS");
					search_dos();
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

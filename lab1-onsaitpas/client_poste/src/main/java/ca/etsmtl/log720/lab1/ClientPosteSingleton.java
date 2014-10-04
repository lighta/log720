package ca.etsmtl.log720.lab1;

import ca.etsmtl.log720.lab1.view.InterfacePoste;

public class ClientPosteSingleton {
		String[] args;
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
		InterfacePoste view;
	
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
}

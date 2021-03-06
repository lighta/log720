package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;


public class Client_Voiture {
	public static void main(String args[]) {
		ClientVoitureSingleton client_voiture = ClientVoitureSingleton.getInstance();
		// Set the singleton args
		client_voiture.setArgs(args);
		
		BanqueReactions banque_reaction;
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
		
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 3) {
				// args[0],args[1] and args[2] are IOR-string
				banque_reaction = BanqueReactionsHelper.narrow(orb.string_to_object(args[0]));
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[1]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[2]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(Variables.NAME_BANK_INF, "service") };
				NameComponent[] name_reac = new NameComponent[] { new NameComponent(Variables.NAME_BANK_REAC, "service") };
				

				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
				banque_reaction = BanqueReactionsHelper.narrow(nc.resolve(name_reac));
				
				// Set the singleton vals
				client_voiture.setBanque_infraction(banque_infraction);
				client_voiture.setBanque_reaction(banque_reaction);
				client_voiture.setBanque_dossier(banque_dossier);
			}
			
			while(client_voiture.getView().isVisible()){
				client_voiture.refresh(); //fill the list with default data
				try {
				    Thread.sleep(Variables.REFRESH_CLIENT_VOITURE*1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
						
			orb.shutdown(true);
			System.out.println("done. ");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

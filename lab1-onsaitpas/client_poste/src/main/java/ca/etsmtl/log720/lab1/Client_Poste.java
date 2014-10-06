package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;

import ca.etsmtl.log720.lab1.view.InterfacePoste;

public class Client_Poste {
	public static void main(String args[]) {
		ClientPosteSingleton client_post = ClientPosteSingleton.getInstance();
		// Set the singleton args
		client_post.setArgs(args);
		
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
					
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 2) {
				// args[0] and args[1] are IOR-string
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[0]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[1]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(Variables.NAME_BANK_INF, "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
				
				// Set the singleton vals
				client_post.setBanqueDossiers(banque_dossier);
				client_post.setBanqueInfractions(banque_infraction);
			}
			
			while(client_post.getView().isVisible()){ // TODO actually I fucking want is not close
				client_post.refresh(); //fill the list with default data
				try {
				    Thread.sleep(Variables.REFRESH_CLIENT_POST*1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			// TODO donot put default close on JFrame, we need to go through here !
			
			
			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

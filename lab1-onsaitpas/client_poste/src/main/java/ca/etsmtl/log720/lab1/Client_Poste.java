package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;

public class Client_Poste {
	public static void main(String args[]) {
		try {
			BanqueDossiers banque_dossier;
			BanqueInfractions banque_infraction;

			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 2) {
				// args[0] and args[1] are IOR-string
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[0]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[1]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				org.omg.CORBA.Object obj_dos = nc.resolve(nc.to_name("banque_dossier"));
				org.omg.CORBA.Object obj_inf = nc.resolve(nc.to_name("banque_infraction"));
				
				banque_dossier = BanqueDossiersHelper.narrow(obj_dos);
				banque_infraction = BanqueInfractionsHelper.narrow(obj_inf);
			}
		
			System.out.println(banque_dossier.toString());
			System.out.println(banque_infraction.toString());

			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

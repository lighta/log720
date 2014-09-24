package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;

public class Client_Voiture {
	public static void main(String args[]) {
		try {
			BanqueReactions banque_reaction;
			BanqueDossiers banque_dossier;
			BanqueInfractions banque_infraction;

			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 3) {
				// args[0],args[1] and args[2] are IOR-string
				banque_reaction = BanqueReactionsHelper.narrow(orb.string_to_object(args[0]));
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[1]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[2]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				org.omg.CORBA.Object obj_dos = nc.resolve(nc.to_name("banque_dossier"));
				org.omg.CORBA.Object obj_inf = nc.resolve(nc.to_name("banque_infraction"));
				org.omg.CORBA.Object obj_reac = nc.resolve(nc.to_name("banque_reaction"));

				banque_reaction = BanqueReactionsHelper.narrow(obj_dos);
				banque_dossier = BanqueDossiersHelper.narrow(obj_inf);
				banque_infraction = BanqueInfractionsHelper.narrow(obj_reac);
			}

			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package ca.etsmtl.log720.lab1;

import java.io.*;

import org.omg.CosNaming.*;

import ca.etsmtl.log720.lab1.reaction.BanqueReactionImpl;

public class Server_Voiture {
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			org.omg.PortableServer.POA poa = org.omg.PortableServer.POAHelper
					.narrow(orb.resolve_initial_references("RootPOA"));

			poa.the_POAManager().activate();

			org.omg.CORBA.Object obj_voiture = poa.servant_to_reference(new BanqueReactionImpl());

			if (args.length == 3) {
				// write the object banque_reaction reference to args[2]
				PrintWriter ps = new PrintWriter(new FileOutputStream(new File(
						args[2])));
				ps.println(orb.object_to_string(obj_voiture));
				ps.close();
			} else {
				// use the naming service

				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				nc.rebind(nc.to_name("banque_reaction"), obj_voiture);
			}

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package ca.etsmtl.log720.lab1;

import java.io.*;

import org.omg.CosNaming.*;

import ca.etsmtl.log720.lab1.dossier.BanqueDossierImpl;
import ca.etsmtl.log720.lab1.infraction.BanqueInfractionImpl;

public class Server_Poste {
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			org.omg.PortableServer.POA poa = org.omg.PortableServer.POAHelper
					.narrow(orb.resolve_initial_references("RootPOA"));

			poa.the_POAManager().activate();

			org.omg.CORBA.Object obj_dos = poa.servant_to_reference(new BanqueDossierImpl());
			org.omg.CORBA.Object obj_inf = poa.servant_to_reference(new BanqueInfractionImpl());

			if (args.length == 2) {
				// write the object banque_dossier reference to args[0] 
				PrintWriter ps_dos = new PrintWriter(new FileOutputStream(new File(
						args[0])));
				ps_dos.println(orb.object_to_string(obj_dos));
				ps_dos.close();
				
				// write the object banque_infraction reference to args[1] 
				PrintWriter ps_inf = new PrintWriter(new FileOutputStream(new File(
						args[1])));
				ps_inf.println(orb.object_to_string(obj_inf));
				ps_inf.close();
			} else {
				// use the naming service
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				nc.rebind(nc.to_name("banque_dossier"), obj_dos);
				nc.rebind(nc.to_name("banque_infraction"), obj_inf);
				// obj_inf ??
			}

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

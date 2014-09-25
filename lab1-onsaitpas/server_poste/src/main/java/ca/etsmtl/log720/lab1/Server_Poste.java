package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.dossier.BanqueDossiersImpl;
import ca.etsmtl.log720.lab1.infraction.BanqueInfractionsImpl;

public class Server_Poste {
	protected static org.omg.PortableServer.POA _poa;
	
	public static POA getPOA(){
		return _poa;
	}
	
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			org.omg.PortableServer.Servant servant_dos, servant_inf;
			org.omg.CORBA.Object obj_dos, obj_inf;

			// Initialize POA
			_poa = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			_poa.the_POAManager().activate();

			// Initialize servant (Remote Object), convert to CORBA reference
			servant_dos = new BanqueDossiersImpl();
			obj_dos = _poa.servant_to_reference(servant_dos);
			servant_inf = new BanqueInfractionsImpl();
			obj_inf = _poa.servant_to_reference(servant_inf);
			
			// Register Rermote Object with naming context
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name_dos = new NameComponent[] { new NameComponent(
					"banque_dossier", "service") };
			NameComponent[] name_inf = new NameComponent[] { new NameComponent(
					"banque_infraction", "service") };
			nc.rebind(name_dos, obj_dos);
			nc.rebind(name_inf, obj_inf);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		orb.run();
	}
}

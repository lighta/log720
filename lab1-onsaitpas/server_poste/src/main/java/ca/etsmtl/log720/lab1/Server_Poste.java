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
			try {
				servant_dos = (BanqueDossiersImpl) Serialisation.decodeFromFile(Variables.PERSISTANCE_PATH+Variables.NAME_BANK_DOS+".save");
				servant_inf = (BanqueInfractionsImpl) Serialisation.decodeFromFile(Variables.PERSISTANCE_PATH+Variables.NAME_BANK_INF+".save");
			} catch(Exception e) {
				servant_dos = new BanqueDossiersImpl();
				servant_inf = new BanqueInfractionsImpl();
			}
			
			
			obj_dos = _poa.servant_to_reference(servant_dos);
			obj_inf = _poa.servant_to_reference(servant_inf);
			
			// Register Rermote Object with naming context
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name_dos = new NameComponent[] { new NameComponent(
					Variables.NAME_BANK_DOS, "service") };
			NameComponent[] name_inf = new NameComponent[] { new NameComponent(
					Variables.NAME_BANK_INF, "service") };
			nc.rebind(name_dos, obj_dos);
			nc.rebind(name_inf, obj_inf);
			
			Serialisation.encodeToFile(servant_dos, Variables.PERSISTANCE_PATH+Variables.NAME_BANK_DOS);
			Serialisation.encodeToFile(servant_inf, Variables.PERSISTANCE_PATH+Variables.NAME_BANK_INF);

						
		} catch (Exception e) {
			e.printStackTrace();
		}
		orb.run();
	}
	
	
}

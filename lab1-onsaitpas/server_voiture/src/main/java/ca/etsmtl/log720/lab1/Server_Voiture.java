package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;

import ca.etsmtl.log720.lab1.reaction.BanqueReactionsImpl;

public class Server_Voiture {
	protected static POA _poa;
	
	public static POA getPOA(){
		return _poa;
	}
	
	public static void main(String[] args) {
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		try {
			org.omg.PortableServer.Servant servant_reac;
			org.omg.CORBA.Object o;

			// Initialize POA
			_poa = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			_poa.the_POAManager().activate();

			// Initialize servant (Remote Object), convert to CORBA reference
			servant_reac = new BanqueReactionsImpl();
			o = _poa.servant_to_reference(servant_reac);
			
			// Register Rermote Object with naming context
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name_reac = new NameComponent[] { new NameComponent(
					Variables.NAME_BANK_REAC, "service") };
			nc.rebind(name_reac, o);
						
			//init_BanqueReaction(servant_reac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		orb.run();
	}
	
//	public static void init_BanqueReaction(Servant servant_reac){
//		try{
//			((BanqueReactionsImpl) servant_reac).ajouterReaction("vente de crack ", 4);
//			((BanqueReactionsImpl) servant_reac).ajouterReaction("roule sens interdit ", 1);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}

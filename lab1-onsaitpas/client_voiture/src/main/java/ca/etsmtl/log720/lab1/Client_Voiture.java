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
				System.out.println("main::client_voiture argslen=3");
				// args[0],args[1] and args[2] are IOR-string
				banque_reaction = BanqueReactionsHelper.narrow(orb.string_to_object(args[0]));
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[1]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[2]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						"BanqueDossiers", "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						"BanqueInfractions", "service") };
				NameComponent[] name_reac = new NameComponent[] { new NameComponent(
						"BanqueInfractions", "service") };
				

				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
				banque_reaction = BanqueReactionsHelper.narrow(nc.resolve(name_reac));
			}

			System.out.println(banque_reaction.toString());
			System.out.println(banque_dossier.toString());
			System.out.println(banque_infraction.toString());
			
			
			int size_dossiers = banque_dossier.dossiers().size();
			System.out.println("size_dossiers="+size_dossiers);
			if(size_dossiers>0){
				int i=0;
				System.out.println("[");
				while(size_dossiers>i){
					System.out.println("\tdos num="+i+": {"+banque_dossier.dossiers().getDossier(i)._toString()+"}");
					i++;
				}
				System.out.println("]");
			}
			
			int size_infraction = banque_infraction.infractions().size();
			System.out.println("size_infraction="+size_infraction);
			if(size_infraction>0){
				int i=0;
				System.out.println("[");
				while(size_infraction>i){
					System.out.println("\tinf num="+i+": {"+banque_infraction.infractions().getInfraction(i)._toString()+"}");
					i++;
				}
				System.out.println("]");
			}
			
			int size_reaction = banque_reaction.reactions().size();
			System.out.println("size_reaction="+size_reaction);
			if(size_reaction>0){
				int i=0;
				System.out.println("[");
				while(size_reaction>i){
					System.out.println("\treac num="+i+": {"+banque_reaction.reactions().getReaction(i)._toString()+"}");
					i++;
				}
				System.out.println("]");
			}
			
			// Ajout d'un reaction
			banque_reaction.ajouterReaction("hello", 1);;
			Reaction reac = banque_reaction.reactions().getReaction(0);
			
			// Description
			System.out.println("Contenu initial de reaction a partir du client: "
										+ reac._toString());
			
						
			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

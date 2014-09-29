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
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						"BanqueDossiers", "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						"BanqueInfractions", "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
			}
		
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
			
			// Ajout d'un dossier
			banque_dossier.ajouterDossier("test", "ptest", "1234", "12345");
			Dossier dos = banque_dossier.dossiers().getDossier(0);
			System.out.println("Contenu initial de dossier a partir du client: "
					+ dos._toString() );
			
			// Ajout d'un dossier
			banque_infraction.ajouterInfraction("proxenetisme", 5);;
			Infraction inf = banque_infraction.infractions().getInfraction(0);
			System.out.println("Contenu initial de infraction a partir du client: "
					+ inf._toString() );


			
			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

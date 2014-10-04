package ca.etsmtl.log720.lab1;

import java.util.Random;

import org.omg.CosNaming.*;

import ca.etsmtl.log720.lab1.view.InterfacePoste;

public class Client_Poste {
	public static void main(String args[]) {
		
		// Set the singleton args
		ClientPosteSingleton.getInstance().setArgs(args);
		
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
		
		// Launch interface
		final InterfacePoste view = new InterfacePoste();
		view.setVisible(true);
		
		// Set the singleton view
		ClientPosteSingleton.getInstance().setView(view);
					
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

			if (args.length == 2) {
				// args[0] and args[1] are IOR-string
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[0]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[1]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_INF, "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
				
				// Set the singleton vals
				ClientPosteSingleton.getInstance().setBanqueDossiers(banque_dossier);
				ClientPosteSingleton.getInstance().setBanqueInfractions(banque_infraction);
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
					
					// Ajout des dossiers a la liste des dossiers
					view.dossiersView.list1.add(banque_dossier.dossiers().getDossier(i)._toString());
					
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
					
					// Ajout des infractions a la liste des infractions
					view.infractionsPosteView.list1.add(banque_infraction.infractions().getInfraction(i)._toString());
					
					i++;
				}
				System.out.println("]");
			}
			
			
			// Ajout d'un dossier
			view.dossiersView.jButton3.addActionListener(new java.awt.event.ActionListener() {
		           public void actionPerformed(java.awt.event.ActionEvent evt) {
		        	   String prenom 	= view.dossiersView.getPrenom();
		        	   String nom 		= view.dossiersView.getNom();
		        	   String plaque 	= view.dossiersView.getPlaque();
		        	   String permis 	= view.dossiersView.getPermis();
		        	   
		        	   ajoutDossier(prenom, nom, plaque, permis);
		        	   refresh();
		           }
		    });
			
			// Ajout d'une infraction
			view.infractionsPosteView.jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					String description = view.infractionsPosteView.getDescription();
					ajoutInfraction(description);
					refresh();
				}
			});

			orb.shutdown(true);
			System.out.println("done. ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void ajoutDossier(String prenom, String nom, String plaque, String permis) {
		// Fetch objects from Singleton
		String[] args = ClientPosteSingleton.getInstance().getArgs();
		BanqueDossiers banque_dossier = ClientPosteSingleton.getInstance().getBanqueDossiers();
		BanqueInfractions banque_infraction = ClientPosteSingleton.getInstance().getBanqueInfractions();
		
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
	
			if (args.length == 2) {
				// args[0] and args[1] are IOR-string
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[0]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[1]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_INF, "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
			}
			
			banque_dossier.ajouterDossier(prenom, nom, plaque, permis);
			Dossier dos = banque_dossier.dossiers().getDossier(0);
			System.out.println("Contenu initial de dossier a partir du client: "
					+ dos._toString() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void ajoutInfraction(String description) {
		// Fetch objects from Singleton
		String[] args = ClientPosteSingleton.getInstance().getArgs();
		BanqueDossiers banque_dossier = ClientPosteSingleton.getInstance().getBanqueDossiers();
		BanqueInfractions banque_infraction = ClientPosteSingleton.getInstance().getBanqueInfractions();
		
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
	
			if (args.length == 2) {
				// args[0] and args[1] are IOR-string
				banque_dossier = BanqueDossiersHelper.narrow(orb.string_to_object(args[0]));
				banque_infraction = BanqueInfractionsHelper.narrow(orb.string_to_object(args[1]));
			} else {
				NamingContextExt nc = NamingContextExtHelper.narrow(orb
						.resolve_initial_references("NameService"));
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_INF, "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
			}
			
			// Random de 1 a 5 pour la gravite
			Random rand = new Random();
			int gravite = rand.nextInt((5 - 1) + 1) + 1;
			
			banque_infraction.ajouterInfraction(description, gravite);
			Infraction inf = banque_infraction.infractions().getInfraction(0);
			System.out.println("Contenu initial de infraction a partir du client: "
					+ inf._toString() );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void refresh() {
		// Fetch objects from Singleton
		String[] args = ClientPosteSingleton.getInstance().getArgs();
		InterfacePoste view = ClientPosteSingleton.getInstance().getView();
		
		view.dossiersView.list1.removeAll();
		view.infractionsPosteView.list1.removeAll();
		
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
				
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(
						Variables.NAME_BANK_INF, "service") };
				
				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
			}
			
			int size_dossiers = banque_dossier.dossiers().size();
			System.out.println("size_dossiers="+size_dossiers);
			if(size_dossiers>0){
				int i=0;
				System.out.println("[");
				while(size_dossiers>i){
					System.out.println("\tdos num="+i+": {"+banque_dossier.dossiers().getDossier(i)._toString()+"}");
					
					// Ajout des dossiers a la liste des dossiers
					view.dossiersView.list1.add(banque_dossier.dossiers().getDossier(i)._toString());
					
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
					
					// Ajout des infractions a la liste des infractions
					view.infractionsPosteView.list1.add(banque_infraction.infractions().getInfraction(i)._toString());
					
					i++;
				}
				System.out.println("]");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

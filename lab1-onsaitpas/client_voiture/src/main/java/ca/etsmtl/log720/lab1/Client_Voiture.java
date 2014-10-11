package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.*;


public class Client_Voiture {
	public static void main(String args[]) {
		ClientVoitureSingleton client_voiture = ClientVoitureSingleton.getInstance();
		// Set the singleton args
		client_voiture.setArgs(args);
		
		BanqueReactions banque_reaction;
		BanqueDossiers banque_dossier;
		BanqueInfractions banque_infraction;
		
		try {
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
				NameComponent[] name_dos = new NameComponent[] { new NameComponent(Variables.NAME_BANK_DOS, "service") };
				NameComponent[] name_inf = new NameComponent[] { new NameComponent(Variables.NAME_BANK_INF, "service") };
				NameComponent[] name_reac = new NameComponent[] { new NameComponent(Variables.NAME_BANK_REAC, "service") };
				

				banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
				banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
				banque_reaction = BanqueReactionsHelper.narrow(nc.resolve(name_reac));
				
				// Set the singleton vals
				client_voiture.setBanque_infraction(banque_infraction);
				client_voiture.setBanque_reaction(banque_reaction);
				client_voiture.setBanque_dossier(banque_dossier);
			}
					
			//test_basic(banque_reaction, banque_dossier, banque_infraction);
			test_Dossier(banque_reaction, banque_dossier, banque_infraction);
			
			while(client_voiture.getView().isVisible()){
				client_voiture.refresh(); //fill the list with default data
				try {
				    Thread.sleep(Variables.REFRESH_CLIENT_VOITURE*1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
						
			orb.shutdown(true);
			System.out.println("done. ");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void test_Dossier(BanqueReactions banque_reaction, BanqueDossiers banque_dossier, BanqueInfractions banque_infraction){
		int id = banque_dossier.dossiers().size();
		System.out.println("Taille dossiers in bank size="+id);
		try {
			banque_dossier.ajouterDossier("john", "do", "123456", "JeMeSouvienS");
			id++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {			
			System.out.println("Test searching by permis");
			Dossier dos_john = banque_dossier.trouverDossierParPermis("123456");
			if(dos_john ==null){
				System.out.println("Recherche par permis as fail");
			}
			else {
				System.out.println("dos_john="+dos_john._toString());
			}
			
			System.out.println("Test searching by id="+id);
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);	
			if(dos_john2 ==null){
				System.out.println("Recherche par ID as fail");
			} else {
				System.out.println("dos_john2="+dos_john2._toString());
			}
				
			System.out.println("Test equals, dos_john, dos_john2");
			if(dos_john.equals(dos_john2) == false){
				System.out.println("La recherche par Permis doesn't match");
			} 
			else {
				System.out.println("Success");
			}
			
			try {
				banque_dossier.ajouterDossier("john2", "do2", "1234", "JeMeTest");
				id++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				banque_dossier.ajouterDossier("notmatch", "fail", "hello", "Testme");
				id++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			{ //test scope
				System.out.println("Test search by plaque");
				CollectionDossier collec_dos = banque_dossier.trouverDossiersParPlaque("JeMe"); //should return2
				if(collec_dos != null ){
					int nb_res = collec_dos.size();
					if(nb_res == 0){
						System.out.println("No result found");
					}
					else {
						int i=0;
						while(nb_res > i){
							System.out.println("Result["+i+"] "+collec_dos.getDossier(i)._toString());
							i++;
						}
					}
				}
			}
			{ //test scope
				System.out.println("Test search by nom,prenom");
				CollectionDossier collec_dos = banque_dossier.trouverDossiersParNom("jo","d"); //should return 2
				if(collec_dos != null ){
					int nb_res = collec_dos.size();
					if(nb_res == 0){
						System.out.println("No result found");
					}
					else {
						int i=0;
						while(nb_res > i){
							System.out.println("Result["+i+"] "+collec_dos.getDossier(i)._toString());
							i++;
						}
					}
				}
			}
			
			
			try {
				System.out.println("Test update niveau john2");
				banque_infraction.ajouterInfraction("proxenetisme", 4); //id0
				banque_infraction.ajouterInfraction("infrac2", 6); //id1
				banque_infraction.ajouterInfraction("infrac3", 2); //id2
				banque_infraction.ajouterInfraction("nodisplay", 2); //id3
				
				dos_john2.ajouterInfractionAListe(0);
				System.out.println("john2 should now have niveau=4 on dossier\n\tdos_john2="+dos_john2._toString()); //
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			{
				dos_john2.ajouterInfractionAListe(1);
				dos_john2.ajouterInfractionAListe(2);
				
				System.out.println("Test list infraction by dossier");
				CollectionInfraction colloc_inf = banque_infraction.trouverInfractionsParDossier(dos_john2);
				if(colloc_inf != null ){
					int nb_res = colloc_inf.size();
					if(nb_res == 0){
						System.out.println("No result found");
					}
					else {
						int i=0;
						while(nb_res > i){
							System.out.println("Result["+i+"] "+colloc_inf.getInfraction(i)._toString());
							i++;
						}
					}
				}
			}
			
			try {
				System.out.println("Test ajout de reaction");
				banque_reaction.ajouterReaction("test1", 2);
				banque_reaction.ajouterReaction("test2", 2);
				banque_reaction.ajouterReaction("test3", 2);
				banque_reaction.ajouterReaction("notdisplay", 2);
				
				for(int i=0; i<3; i++)
					dos_john2.ajouterReactionAListe(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			{
				System.out.println("Test list reaction by dossier");
				CollectionReaction colloc_reac = banque_reaction.trouverReactionsParDossier(dos_john2);
				if(colloc_reac != null ){
					int nb_res = colloc_reac.size();
					if(nb_res == 0){
						System.out.println("No result found");
					}
					else {
						int i=0;
						while(nb_res > i){
							System.out.println("Result["+i+"] "+colloc_reac.getReaction(i)._toString());
							i++;
						}
					}
				}
			}
		}
	}
	
	static void test_basic(BanqueReactions banque_reaction, BanqueDossiers banque_dossier, BanqueInfractions banque_infraction){
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
		System.out.println("Contenu initial de reaction a partir du client: "+ reac._toString());

	}
}

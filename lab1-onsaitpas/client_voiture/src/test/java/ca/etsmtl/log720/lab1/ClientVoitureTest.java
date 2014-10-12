package ca.etsmtl.log720.lab1;

import junit.framework.TestCase;

import org.omg.CosNaming.*;


public class ClientVoitureTest extends TestCase {
    private org.omg.CORBA.ORB orb;
	private BanqueReactions banque_reaction;
	private BanqueDossiers banque_dossier;
	private BanqueInfractions banque_infraction;
    
    public void setUp(){
		try {
			String[] args = { "-ORBInitialPort",
						"31501",
						"-ORBInitialHost",
						"localhost" };
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			NameComponent[] name_dos = new NameComponent[] { new NameComponent(Variables.NAME_BANK_DOS, "service") };
			NameComponent[] name_inf = new NameComponent[] { new NameComponent(Variables.NAME_BANK_INF, "service") };
			NameComponent[] name_reac = new NameComponent[] { new NameComponent(Variables.NAME_BANK_REAC, "service") };
			
			banque_dossier = BanqueDossiersHelper.narrow(nc.resolve(name_dos));
			banque_infraction = BanqueInfractionsHelper.narrow(nc.resolve(name_inf));
			banque_reaction = BanqueReactionsHelper.narrow(nc.resolve(name_reac));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void tearDown(){
    //	orb.shutdown(true);
    	System.out.println("orb deleted. ");
    }
	
    public void testInfractionAddToList(){
    	try {
    		int size = banque_infraction.infractions().size();
			banque_infraction.ajouterInfraction("proxenetisme", 4); //id0
			banque_infraction.ajouterInfraction("infrac2", 6); //id1
			banque_infraction.ajouterInfraction("infrac3", 2); //id2
			banque_infraction.ajouterInfraction("nodisplay", 2); //id3
			assertTrue( 4+size == banque_infraction.infractions().size() );
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
     
	public void testInfractionTrouveByID() {
		try {
			Infraction tmp_inf = banque_infraction.trouverInfractionParId(2);
			assertTrue( tmp_inf != null );
			System.out.println("Infraction_id(2)="+tmp_inf._toString());
	//		tmp_inf._release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testReactionAddToList() {
		try {
			int size = banque_reaction.reactions().size();
			banque_reaction.ajouterReaction("test1", 2);
			banque_reaction.ajouterReaction("test2", 2);
			banque_reaction.ajouterReaction("test3", 2);
			banque_reaction.ajouterReaction("notdisplay", 2);
			assertTrue( 4+size == banque_reaction.reactions().size() );
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testReactionTrouveByID() {
		try {
			Reaction tmp_reac = banque_reaction.trouverReactionParId(3);	
			assertTrue( tmp_reac != null );
			System.out.println("Infraction_id(2)="+tmp_reac._toString());
	//		tmp_reac._release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierAddToList() {
		try {
			int size = banque_dossier.dossiers().size();
			banque_dossier.ajouterDossier("john", "do", "123456", "JeMeSouvienS");
			banque_dossier.ajouterDossier("notmatch", "fail", "hello", "Testme");
			banque_dossier.ajouterDossier("john2", "do2", "1234", "JeMeTest");
			assertTrue( size+3 == banque_dossier.dossiers().size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierTrouveByID() {
		try {
			Dossier dos_john = banque_dossier.trouverDossierParId(0);
			assertTrue( dos_john != null );
			System.out.println("dos_john="+dos_john._toString());
	//		dos_john._release();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDossierTrouveByPermis() {
		try {
			Dossier dos_john = banque_dossier.trouverDossierParPermis("123456");
			assertTrue( dos_john != null );
			System.out.println("dos_john="+dos_john._toString());
	//		dos_john._release();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDossierCompareDossier() {
		try {
			Dossier dos_john = banque_dossier.trouverDossierParPermis("123456");
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			assertTrue( dos_john.equals(dos_john2) );
	//		dos_john._release();
	//		dos_john2._release();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDossierSearchByPlaque() {
		try {
			CollectionDossier collec_dos = banque_dossier.trouverDossiersParPlaque("JeMe"); //should return2
			assertTrue(collec_dos != null);
			
			int nb_res = collec_dos.size();
			assertTrue(nb_res == 2);
			
			int i=0;
			while(nb_res > i){
				System.out.println("Result["+i+"] "+collec_dos.getDossier(i)._toString());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDossierSearchByNom() {
		try {
			CollectionDossier collec_dos = banque_dossier.trouverDossiersParNom("jo","d"); //should return 2
			assertTrue(collec_dos != null);
			
			int nb_res = collec_dos.size();
			assertTrue(nb_res == 2);
			
			int i=0;
			while(nb_res > i){
				System.out.println("Result["+i+"] "+collec_dos.getDossier(i)._toString());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDossierAddInfraction(){
		try {
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			int size = dos_john2.getListeInfraction().length;
			
			dos_john2.ajouterInfractionAListe(0); //inf0 = niveau 4
			dos_john2.ajouterInfractionAListe(1);
			dos_john2.ajouterInfractionAListe(2);		
			assertEquals(size+3, dos_john2.getListeInfraction().length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierUpdate(){
		try {
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			assertTrue(dos_john2.niveau() == 6); //la plus gross infraction est 6
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierTrouveListInfraction(){
		try {
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			CollectionInfraction colloc_inf = banque_infraction.trouverInfractionsParDossier(dos_john2);
			assertTrue(colloc_inf != null);

			int i=0;
			int nb_res = colloc_inf.size();
			assertTrue(nb_res >= 3); //3 infraction commise
			while(nb_res > i){
				System.out.println("Result["+i+"] "+colloc_inf.getInfraction(i)._toString());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierAddReaction(){
		try {
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			int size = dos_john2.getListeReaction().length;
			for(int i=0; i<3; i++)
				dos_john2.ajouterReactionAListe(i);
			assertEquals(size+3,dos_john2.getListeReaction().length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDossierTrouveListReaction(){
		try {
			Dossier dos_john2 = banque_dossier.trouverDossierParId(0);
			CollectionReaction colloc_reac = banque_reaction.trouverReactionsParDossier(dos_john2);
			assertTrue(colloc_reac != null);

			int i=0;
			int nb_res = colloc_reac.size();
			assertTrue(nb_res >= 3); //3 reaction enregistrer
			while(nb_res > i){
				System.out.println("Result["+i+"] "+colloc_reac.getReaction(i)._toString());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

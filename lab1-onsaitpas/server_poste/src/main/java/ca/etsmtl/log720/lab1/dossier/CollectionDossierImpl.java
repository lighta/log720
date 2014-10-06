package ca.etsmtl.log720.lab1.dossier;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import ca.etsmtl.log720.lab1.CollectionDossierPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.DossierHelper;
import ca.etsmtl.log720.lab1.NoPermisExisteDejaException;
import ca.etsmtl.log720.lab1.Server_Poste;

public class CollectionDossierImpl extends CollectionDossierPOA implements Serializable {
	private static final long serialVersionUID = -5649986628277676620L;
	private ArrayList<DossierImpl> _list_dossiers;
	
	public ArrayList<DossierImpl> getListDossiers() {
		return _list_dossiers;
	}

	public CollectionDossierImpl() {
		super();
		_list_dossiers = new ArrayList<DossierImpl>();
	}

	public Dossier getDossier(int index) {
		try {
			// Trouver le reaction correspondant au parametre "index"
			DossierImpl dossier = _list_dossiers.get(index);
			// REcuperer le POA cree dans le serveur
			POA rootpoa = Server_Poste.getPOA();
			// Activer l'objet et retourner l'objet CORBA
			org.omg.CORBA.Object obj = rootpoa.servant_to_reference(dossier);
			// Retourner un Dossier
			return DossierHelper.narrow(obj);
		} catch (Exception e) {
			System.out.println("Erreur retour de l'objet Dossier : " + e);
			return null;
		}
	}

	public int size() {
		return _list_dossiers.size();
	}
	
	public void ajouterDossier(String nom, String prenom, String noPermis,
			String noPlaque)  throws NoPermisExisteDejaException {
		int id=_list_dossiers.size();
		DossierImpl tmp_dos = new DossierImpl(id, nom, noPermis, noPlaque, prenom);
		for(DossierImpl cur_dos : _list_dossiers){
			if(cur_dos.noPermis().compareTo(noPermis) == 0){
				throw new NoPermisExisteDejaException();
			}
		}
		_list_dossiers.add(tmp_dos);
	}
	
	public void ajouterDossier(DossierImpl dossier)  throws NoPermisExisteDejaException {
		if(_list_dossiers.contains(dossier))
			throw new NoPermisExisteDejaException();
		_list_dossiers.add(dossier);
	}
	
	public void retirerDossier(int index){
		_list_dossiers.remove(index);
	}
	
	public Dossier trouverDossierParId(int idDossier) {
		int i=0;
		for(DossierImpl cur_dos : _list_dossiers){
			if(cur_dos.id() == idDossier){
				return getDossier(i); //get the real dossier from orb
			}
			i++;
		}
		return null;
	}
	
	public Dossier trouverDossierParPermis(String permis) {
		int i=0;
		for(DossierImpl cur_dos : _list_dossiers){
			if(cur_dos.noPermis() == permis){
				return getDossier(i); //get the real dossier from orb
			}
			i++;
		}
		return null;
	}
	
	public CollectionDossierImpl matches(String nom, String prenom, String noPlaque){
		CollectionDossierImpl tmp_collecdos = new CollectionDossierImpl();
		int i=0;
		
		while(_list_dossiers.size() > i++){
			boolean match=false;
			DossierImpl cur = _list_dossiers.get(i);
			if(cur==null) //shoudln't ever happen
				continue;
			
			if (noPlaque != null) { //match by plaque
				if (cur.noPlaque != null && noPlaque.contains(cur.noPlaque)){
					match=true;
				}
				else continue;
			}
			if (nom != null) { //match by name
				if (cur.nom != null && nom.contains(cur.nom)){
					match=true;
				}
				else continue;
			}
			if (prenom != null) { //match by 1st name
				if (cur.prenom != null && prenom.contains(cur.prenom)){
					match=true;
				}
				else continue;
			}
			
			if(match){
				try {
					tmp_collecdos.ajouterDossier(cur);
				} catch (NoPermisExisteDejaException e) { //already there but shouldn't happen
					e.printStackTrace();
				}
			}
		}
		return tmp_collecdos;
	}
}

package ca.etsmtl.log720.lab1.infraction;

import ca.etsmtl.log720.lab1.BanqueInfractionsPOA;
import ca.etsmtl.log720.lab1.CollectionInfraction;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;

public class BanqueInfractionImpl extends BanqueInfractionsPOA {
	CollectionInfractionImpl infractions;
	
	public BanqueInfractionImpl() {
		super();
		infractions = new CollectionInfractionImpl();
	}

	public CollectionInfraction infractions() {
		return infractions._this();
	}

	public CollectionInfraction trouverInfractionsParDossier(Dossier mydossier) {
		CollectionInfractionImpl list_infraction = new CollectionInfractionImpl();
		int i=0;
		while(infractions.size() < i++){
		//	infractions.getInfraction(i)
			;  // TODO ajouter infractions qui fit dossiers
		}
		return (CollectionInfraction) list_infraction;
	}

	public Infraction trouverInfractionParId(int idInfraction) {
		return infractions.trouverInfractionParId(idInfraction);
	}

	public void ajouterInfraction(String description, int niveau)
			throws NiveauHorsBornesException {
		infractions.ajouterInfraction(description, niveau);	
	}

	@Override
	public String toString() {
		return "BanqueInfractionImpl [infractions=" + infractions + "]";
	}
}

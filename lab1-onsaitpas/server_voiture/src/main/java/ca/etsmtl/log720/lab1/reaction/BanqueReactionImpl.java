package ca.etsmtl.log720.lab1.reaction;

import ca.etsmtl.log720.lab1.BanqueReactionsPOA;
import ca.etsmtl.log720.lab1.CollectionReaction;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Reaction;

public class BanqueReactionImpl extends BanqueReactionsPOA {
	CollectionReactionImpl reactions;
	
	public BanqueReactionImpl() {
		super();
		this.reactions = new CollectionReactionImpl();
	}

	public CollectionReaction reactions() {
		// TODO Auto-generated method stub
		return null;
	}

	public void ajouterReaction(String reaction, int gravite) {
		// TODO Auto-generated method stub
		
	}

	public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
		// TODO Auto-generated method stub
		return null;
	}

	public Reaction trouverReactionParId(int idReaction) {
		// TODO Auto-generated method stub
		return null;
	}

}

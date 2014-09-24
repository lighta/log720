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
		return reactions._this();
	}

	public void ajouterReaction(String reaction, int gravite) {
		reactions.ajouterReaction(reaction, gravite);
	}

	public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
		CollectionReactionImpl tmp_col_reac = reactions.trouverReactionsParDossier(myDossier);
		if(tmp_col_reac.size()>0)
			return tmp_col_reac._this();
		return null;
	}

	public Reaction trouverReactionParId(int idReaction) {
		return reactions.trouverReactionParId(idReaction);
	}

}

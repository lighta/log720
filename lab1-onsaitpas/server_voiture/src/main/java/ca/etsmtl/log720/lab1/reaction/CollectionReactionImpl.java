package ca.etsmtl.log720.lab1.reaction;

import java.util.Arrays;

import ca.etsmtl.log720.lab1.CollectionReactionPOA;
import ca.etsmtl.log720.lab1.Dossier;
import ca.etsmtl.log720.lab1.Reaction;

public class CollectionReactionImpl extends CollectionReactionPOA {
	private ReactionImpl reactions[];
	private int size;
	
	public CollectionReactionImpl() {
		super();
		size=0;
	}

	public int size() {
		return size;
	}

	public Reaction getReaction(int index) {
		if(size < index)
			return null;
		return reactions[index]._this();
	}
	
	public void ajouterReaction(String reaction, int gravite) {
		reactions[size] = new ReactionImpl(reaction,gravite);
		size++;
	}
	
	public void retirerReaction(int index){
		if(index==0) //nothing to do
			return;
		int i= index;
		while(i < size-1){
			reactions[i] = reactions[i+1];
			i++;
		}
		reactions[size] = null; //the last one is now 1 index below
		size--;
	}
	
	CollectionReactionImpl trouverReactionsParDossier(Dossier myDossier){
		// TODO added stub
		return null;
	}

	public Reaction trouverReactionParId(int idReaction){
		int i=0;
		while(size > i++){
			if(reactions[i].id == idReaction)
				return getReaction(i);
		}
		return null;
	}

	@Override
	public String toString() {
		return "CollectionReactionImpl [reactions="
				+ Arrays.toString(reactions) + ", size=" + size + "]";
	}
	
	
}

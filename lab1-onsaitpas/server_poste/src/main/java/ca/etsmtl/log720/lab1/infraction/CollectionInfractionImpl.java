package ca.etsmtl.log720.lab1.infraction;

import java.util.Arrays;

import ca.etsmtl.log720.lab1.CollectionInfractionPOA;
import ca.etsmtl.log720.lab1.Infraction;
import ca.etsmtl.log720.lab1.NiveauHorsBornesException;

public class CollectionInfractionImpl extends CollectionInfractionPOA {
	private InfractionImpl infractions[];
	private int size;
	
	public CollectionInfractionImpl() {	
		super();
		size=0;
	}

	public Infraction getInfraction(int index) {
		if(size < index)
			return null;
		return infractions[index]._this();
	}

	public int size() {
		return size;
	}
	
	public void ajouterInfraction(String description, int niveau) 
			throws NiveauHorsBornesException {
		if(niveau < InfractionImpl.NIVEAU_MIN || niveau > InfractionImpl.NIVEAU_MAX)
			throw new NiveauHorsBornesException();
		infractions[size] = new InfractionImpl(size, description, niveau);
		size++;
	}
	
	public void retirerInfraction(int index){
		if(index==0) //nothing to do
			return;
		int i= index;
		while(i < size-1){
			infractions[i] = infractions[i+1];
			i++;
		}
		infractions[size] = null; //the last one is now 1 index below
		size--;
	}

	public Infraction trouverInfractionParId(int idInfraction) {
		int i=0;
		while(size > i++){
			if(infractions[i].id == idInfraction)
				return getInfraction(i);
		}
		return null;
	}

	@Override
	public String toString() {
		return "CollectionInfractionImpl [infractions="
				+ Arrays.toString(infractions) + ", size=" + size + "]";
	}
}

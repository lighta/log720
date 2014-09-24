package ca.etsmtl.log720.lab1.infraction;

import ca.etsmtl.log720.lab1.InfractionPOA;

public class InfractionImpl extends InfractionPOA {
	protected int id;
	protected String description;
	protected int niveau;
	
	public InfractionImpl() {
		super();
		id = 0;
		description = "";
		niveau = 0;
	}
	
	public InfractionImpl(int id, String description, int niveau) {
		super();
		this.id = id;
		this.description = description;
		this.niveau = niveau;
	}

	public int id() {
		return id;
	}

	public String description() {
		return description;
	}

	public int niveau() {
		return niveau;
	}

	public String _toString() {
		// TODO Auto-generated method stub
		return null;	
	}

	@Override
	public String toString() {
		return "InfractionImpl [id=" + id + ", description=" + description
				+ ", niveau=" + niveau + "]";
	}

	
}

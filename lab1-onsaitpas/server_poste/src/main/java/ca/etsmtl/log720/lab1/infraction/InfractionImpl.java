package ca.etsmtl.log720.lab1.infraction;

import ca.etsmtl.log720.lab1.InfractionPOA;

public class InfractionImpl extends InfractionPOA {
	public static final int NIVEAU_MAX = 100;
	public static final int NIVEAU_MIN = 3;
	protected int id;
	protected String description;
	protected int niveau;
	
	public InfractionImpl() {
		super();
		id = 0;
		description = "";
		niveau = NIVEAU_MIN;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + niveau;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfractionImpl other = (InfractionImpl) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (niveau != other.niveau)
			return false;
		return true;
	}
}

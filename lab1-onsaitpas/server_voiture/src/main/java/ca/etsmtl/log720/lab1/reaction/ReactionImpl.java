package ca.etsmtl.log720.lab1.reaction;

import ca.etsmtl.log720.lab1.ReactionPOA;

public class ReactionImpl extends ReactionPOA {
	int id;
	String description;
	int niveau;
	
	public ReactionImpl() {
		super();
		id = 0;
		description = "";
		niveau = 0;
	}

	public ReactionImpl(int id, String description, int niveau) {
		super();
		this.id = id;
		this.description = description;
		this.niveau = niveau;
	}


	public ReactionImpl(String description, int niveau) {
		super();
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
		return "ReactionImpl [id=" + id + ", description=" + description
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
		ReactionImpl other = (ReactionImpl) obj;
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

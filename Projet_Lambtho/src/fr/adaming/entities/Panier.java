package fr.adaming.entities;

import java.io.Serializable;

public class Panier implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_panier;

	public Panier() {
		super();
	}

	public Panier(long id_panier) {
		super();
		this.id_panier = id_panier;
	}

	public long getId_panier() {
		return id_panier;
	}

	public void setId_panier(long id_panier) {
		this.id_panier = id_panier;
	}

	@Override
	public String toString() {
		return "Panier [id_panier=" + id_panier + "]";
	}

}

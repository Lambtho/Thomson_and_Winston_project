package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Attributs
	@Id
	@Column(name = "id_prd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduit;
	@Column(name = "designation_prd")
	private String designation;
	@Column(name = "description_prd")
	private String description;
	@Column(name = "prix_prd")
	private double prix;
	@Column(name = "quantite_prd")
	private int quantite;
	@Column(name = "selectionne_prd")
	private boolean selectionne;
	@Column(name = "photo_prd")
	private String photo;

	// Constructeurs

	public Produit() {
		super();
	}

	public Produit(String designation, String description, double prix, int quantite, boolean selectionne,
			String photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(long idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, String photo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	// Getters & Setters

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo=" + photo
				+ "]";
	}

}
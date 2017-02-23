package fr.adaming.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "commandes")
public class Commande implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cmd")
	private long idCommande;
	@Column(name = "date_cmd")
	private Calendar dateCommande;
	
	@ManyToMany(mappedBy="listeCommandes", cascade=CascadeType.ALL)
	private List<Produit> listeProduits;
	
	@ManyToOne 
	@JoinColumn (name="id_cl_fk", referencedColumnName="id_cl")
	private Client client;

	@OneToMany(mappedBy="commande")
	private List<LigneCommande> listeLignesCommandes;
	
	// Constructeurs
	public Commande() {
		super();
	}

	public Commande(Calendar dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}

	public Commande(long idCommande, Calendar dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}

	// Getters & Setters
	public long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}

	public Calendar getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Calendar dateCommande) {
		this.dateCommande = dateCommande;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}

}

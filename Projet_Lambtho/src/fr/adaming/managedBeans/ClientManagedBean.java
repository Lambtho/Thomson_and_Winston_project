package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.LigneCommande;
import fr.adaming.entities.Produit;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clientMB")
@SessionScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	IClientService clientService;

	Client client;
	Produit produit;
	Categorie categorie;
	LigneCommande ligneCommande;
	Commande commande;

	List<Categorie> listeCategories;
	List<Produit> listeProduits;
	List<LigneCommande> listeLignesCmd;
	List<Produit> listeProduitsCmd;

	String indexCat;
	String recherche;

	public ClientManagedBean() {
		client = new Client();
		produit = new Produit();
		categorie = new Categorie();
		ligneCommande = new LigneCommande();
		commande = new Commande(Calendar.getInstance());
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public String getIndexCat() {
		return indexCat;
	}

	public void setIndexCat(String indexCat) {
		this.indexCat = indexCat;
	}

	public String getRecherche() {
		return recherche;
	}

	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}

	// Méthodes
	@PostConstruct
	public void getAllCategories() {
		this.listeCategories = clientService.getAllCategories();
		this.listeProduits = clientService.getProductByKeyWordService("a");
	}

	public String getProdByCat() {
		System.out.println("index cliqué============================================>" + this.indexCat);
		this.listeProduits = clientService.getProductByCatService(Integer.parseInt(this.indexCat));
		System.out.println("liste produits =======================>" + listeProduits);
		return null;
	}
	
	public String getByKeyWord(){
		this.listeProduits = clientService.getProductByKeyWordService(recherche);
		return null;
	}
	
	public String selectProduct(){
		this.listeProduitsCmd.add(this.produit);
//		this.listeLignesCmd.add(e)
		return null;
	}
}

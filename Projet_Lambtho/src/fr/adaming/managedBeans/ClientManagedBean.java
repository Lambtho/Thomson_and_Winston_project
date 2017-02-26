package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
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
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clientMB")
@SessionScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	IClientService clientService;

	@EJB
	IAdministrateurService adminService;

	Client client;
	Produit produit;
	Categorie categorie;
	LigneCommande ligneCommande;
	Commande commande;

	List<Categorie> listeCategories;
	List<Produit> listeProduits;
	List<LigneCommande> listeLignesCmd;
	List<Produit> listeProduitsCmd;
	List<Commande> listeCommande;

	String indexCat;
	String recherche;

	public ClientManagedBean() {
		client = new Client();
		produit = new Produit();
		categorie = new Categorie();
		ligneCommande = new LigneCommande();
		commande = new Commande(Calendar.getInstance());
		listeProduitsCmd = new ArrayList<Produit>();
		listeLignesCmd = new ArrayList<LigneCommande>();
		listeCommande = new ArrayList<Commande>();
		this.produit.setListeLigneCommandes(listeLignesCmd);
		this.commande.setListeLignesCommandes(listeLignesCmd);
		this.client.setListeCommande(listeCommande);
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
		this.listeProduits = clientService.getProductByKeyWordService("");
	}

	public void getAllProd() {
		this.listeProduits = clientService.getProductByKeyWordService("");
	}

	public void getCart() {
		List<LigneCommande> llcmd = this.commande.getListeLignesCommandes();
		this.listeProduits.clear();
		for(LigneCommande lc:llcmd){
			this.listeProduits.add(lc.getProduit());
		}
		
		System.out.println("==============================>" + this.listeProduits);
	}

	public String getProdByCat() {
		System.out.println("index cliqué============================================>" + this.indexCat);
		this.listeProduits = clientService.getProductByCatService(Integer.parseInt(this.indexCat));
		System.out.println("liste produits =======================>" + listeProduits);
		return null;
	}

	public String getByKeyWord() {
		this.listeProduits = clientService.getProductByKeyWordService(recherche);
		return null;
	}

	public String order() {
		
		this.commande.setClient(this.client);		
		
		List<Commande> listCmd = new ArrayList<Commande>();
		listCmd = this.client.getListeCommande();
		listCmd.add(this.commande);
		this.client.setListeCommande(listCmd);
		clientService.orderService(client, commande);
		// débiter les stocks
		return "Accueil";
	}

	public String selectProduct() {

		// Ajout du produit & de la commande dans la ligne commande
		LigneCommande lc = new LigneCommande();
		lc.setProduit(this.produit);
		lc.setCommande(this.commande);
		int quantite = this.produit.getQuantite();
		double prix = quantite * this.produit.getPrix();
		lc.setPrix(prix);
		lc.setQuantite(quantite);

		// Ajout de la ligne commande dans le produit
		List<LigneCommande> llcmd = new ArrayList<LigneCommande>();
		llcmd = this.produit.getListeLigneCommandes();
		llcmd.add(lc);
		this.produit.setListeLigneCommandes(llcmd);

		// Ajout de la ligne commande dans la commande
		

		llcmd = this.commande.getListeLignesCommandes();
		llcmd.add(lc);
		this.commande.setListeLignesCommandes(llcmd);

		// Le produit est sélectionné
		this.produit.setSelectionne(true);

		
		 System.out.println(
		 "====================================================================================================================");
		 System.out.println(commande);
		 for (LigneCommande lc1 : commande.getListeLignesCommandes()) {
		 System.out.println(lc1.getProduit());
		 }
		 for (LigneCommande lc2 : commande.getListeLignesCommandes()) {
		 System.out.println(lc);
		 }
		 System.out.println(
		 "====================================================================================================================");

		// Regeneration de la liste de produits
		listeProduits = clientService.getProductByKeyWordService("");
		return null;
	}
}

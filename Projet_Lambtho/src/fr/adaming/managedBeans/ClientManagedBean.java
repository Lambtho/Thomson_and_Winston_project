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
		this.listeProduits = this.commande.getListeProduits();
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
		List<Commande> listCmd = new ArrayList<Commande>();
		listCmd = this.client.getListeCommande();
		listCmd.add(this.commande);
		this.client.setListeCommande(listCmd);
		this.commande.setClient(this.client);
		clientService.orderService(client, commande);
//		débiter les stocks
		return "Accueil";
	}

	public String selectProduct() {

		List<LigneCommande> llcmd = new ArrayList<LigneCommande>();
		for (int i = 0; i < listeLignesCmd.size(); i++) {
			LigneCommande lc = listeLignesCmd.get(i);
			Produit p = lc.getProduit();
			System.out.println("+++++++++++++++++++>" + p.getIdProduit());
			System.out.println("+++++++++++++++++++>" + this.produit.getIdProduit());

			if (p.getIdProduit() == this.produit.getIdProduit()) {

				System.out.println("coucou");
				int quantt = lc.getQuantite() + this.produit.getQuantite();
				lc.setQuantite(quantt);
				lc.setPrix(quantt * this.produit.getPrix());

				break;
			}
			lc.setCommande(commande);
			lc.setProduit(produit);
			llcmd.add(lc);
		}
		this.listeLignesCmd.clear();
		this.listeLignesCmd = llcmd;

		// Le produit est sélectionné
		this.produit.setSelectionne(true);
		// Ajout du produit dans une liste
		this.listeProduitsCmd.add(this.produit);
		
		
		List<Commande> lcmd = new ArrayList<>();
		this.produit.getListeCommandes();
//		this.produit.setListeCommandes(listeCommandes);

		
		// Ajout des lignes de commande et des produits dans la commande
		this.commande.setListeLignesCommandes(listeLignesCmd);
		this.commande.setListeProduits(listeProduitsCmd);
		// System.out.println(
		// "====================================================================================================================");
		// System.out.println(commande);
		// for (Produit p : commande.getListeProduits()) {
		// System.out.println(p);
		// }
		// for (LigneCommande lc : commande.getListeLignesCommandes()) {
		// System.out.println(lc);
		// }
		// System.out.println(
		// "====================================================================================================================");

		// Regeneration de la liste de produits
		listeProduits = clientService.getProductByKeyWordService("");
		return null;
	}
}

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

	// Injection des variable d'accès au back-end dans le contener web qui va
	// les instancier
	@EJB
	IClientService clientService;

	@EJB
	IAdministrateurService adminService;

	// Variables
	Client client;
	Produit produit;
	Categorie categorie;
	LigneCommande ligneCommande;
	Commande commande;

	// Listes utilisées dans les menus déroulants
	List<Categorie> listeCategories;
	List<Produit> listeProduits;
	// Listes utilisées pour les liens entre les tables de la BDD
	List<LigneCommande> listeLignesCmd;
	List<Produit> listeProduitsCmd;
	List<Commande> listeCommande;

	// Index des catégories dans les menus déroulant
	String indexCat;
	// mot clef recherché
	String recherche = "";
	// Numéro de la ligne dans les tableaux de produit
	int indexrow;

	// Constructeur vide => instanciation (et initialisation)
	public ClientManagedBean() {
		// Instanciation
		client = new Client();
		produit = new Produit();
		categorie = new Categorie();
		ligneCommande = new LigneCommande();

		// Création d'une nouvelle commande
		commande = new Commande(Calendar.getInstance());

		// Instenciation des listes
		listeProduitsCmd = new ArrayList<Produit>();
		listeLignesCmd = new ArrayList<LigneCommande>();
		listeCommande = new ArrayList<Commande>();

		// Association des listes qui font les liens entre les tables de la BDD
		this.produit.setListeLigneCommandes(listeLignesCmd);
		this.commande.setListeLignesCommandes(listeLignesCmd);
		this.client.setListeCommande(listeCommande);
	}

	// Getters & Setters
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

	public int getIndexrow() {
		return indexrow;
	}

	public void setIndexrow(int indexrow) {
		this.indexrow = indexrow;
	}

	// Méthodes

	// Méthode executée à la construction de la page
	@PostConstruct
	public void getAllCategories() {

		// Initialisation de la liste de toutes les catégories
		this.listeCategories = clientService.getAllCategories();

		// Initialisation de la liste des produits possédant le mot clef saisi
		// dans la barre de recherche
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);

	}

	// Récupère la liste de tous les produits
	public void getAllProd() {
		this.listeProduits = clientService.getProductByKeyWordService("");
	}

	// Récupère la liste des produits possédant le mot clef saisi dans la barre
	// de recherche
	public void getProd() {
		System.out.println("=================+>" + this.listeProduits);
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);
		System.out.println("=================+>" + this.listeProduits);

	}

	// Récupère le contenu du panier
	public void getCart() {

		// Récupère la liste des lignes de commandes
		List<LigneCommande> llcmd = this.commande.getListeLignesCommandes();

		// Réinitialisation de la liste des produits (qui s'affiche dans le
		// tableau
		this.listeProduits.clear();

		// On met les produits récupérés dans la liste des lignes de commande
		// dans la liste de produits à afficher
		for (LigneCommande lc : llcmd) {
			this.listeProduits.add(lc.getProduit());
		}
	}

	// Récupère la liste des produits correspondants à la catégorie sélectionnée
	public String getProdByCat() {
		this.listeProduits = clientService.getProductByCatService(Integer.parseInt(this.indexCat));
		return null;
	}

	// Récupère la liste des produits contenant le mot saisi dans la barre de
	// recherche
	public String getByKeyWord() {
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);

		// Retour à la page de recherhce pour afficher le résultat
		return "Recherche";
	}

	// Méthode pour enregistrer la commande
	public String order() {

		// On associe le client à la commande
		this.commande.setClient(this.client);

		// Création de la liste des commandes passées par le client
		List<Commande> listCmd = new ArrayList<Commande>();
		listCmd = this.client.getListeCommande();

		// Ajout de la commande passée dans la liste de toutes les commandes
		listCmd.add(this.commande);

		// Association du client et des commandes passées
		this.client.setListeCommande(listCmd);

		// Enregistrement de la commande dans la BDD
		clientService.orderService(client);

		// débiter les stocks pour chacun des produits commandés
		for (LigneCommande lc : this.listeLignesCmd) {

			// On récupère un produit de la liste des lignes de commande
			Produit p = new Produit();
			p = lc.getProduit();

			// Onrécupère la quantité du produit acheté par le client
			int quantt = p.getQuantite();

			// On récupère la quantité de produits disponible dans la BDD
			int quanttStock = clientService.getProductByIdService(p).get(0).getQuantite();

			// Nombre de produits restants après l'achat
			p.setQuantite(quanttStock - quantt);

			// Mise à jour du produit dans la BDD après achat
			int verif = adminService.updateProductService(p);

		}

		// Réinitialisation des variables après les variables (même chose que
		// dans le constructeur vide)
		this.client = new Client();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.ligneCommande = new LigneCommande();
		this.commande = new Commande(Calendar.getInstance());
		this.listeProduitsCmd = new ArrayList<Produit>();
		this.listeLignesCmd = new ArrayList<LigneCommande>();
		this.listeCommande = new ArrayList<Commande>();
		this.produit.setListeLigneCommandes(listeLignesCmd);
		this.commande.setListeLignesCommandes(listeLignesCmd);
		this.client.setListeCommande(listeCommande);

		//On met la liste de tous les produits dans la liste à afficher
		this.listeProduits = clientService.getProductByKeyWordService("");

		//Retour à l'accueil
		return "Accueil";
	}

	//Ajout du produit dans le panier
	public String selectProduct() {

		// Ajout du produit & de la commande dans la ligne commande
		LigneCommande lc = new LigneCommande();
		lc.setProduit(this.produit);
		lc.setCommande(this.commande);
		
		//On récupère la quantité et le prix et on calcule le prix total concernant ce produit
		int quantite = this.produit.getQuantite();
		double prix = quantite * this.produit.getPrix();
		
		//On met la quantité et le prix calculé dans la ligne de commande
		lc.setPrix(prix);
		lc.setQuantite(quantite);

		//On récupère la liste des lignes de commande (tous les produits du panier)
		List<LigneCommande> llcmd = new ArrayList<LigneCommande>();
		llcmd = this.produit.getListeLigneCommandes();
		//Ajout de la nouvelle ligne de commande
		llcmd.add(lc);
		// Ajout de la ligne commande dans le produit
		this.produit.setListeLigneCommandes(llcmd);

		//On récupère la liste de ligne de commande dans la commande actuelle
		llcmd = this.commande.getListeLignesCommandes();
		//Ajout de la ligne de commande dans la liste
		llcmd.add(lc);
		// Ajout de la liste de ligne commande dans la commande
		this.commande.setListeLignesCommandes(llcmd);

		// Le produit est sélectionné
		this.produit.setSelectionne(true);

		// Regeneration de la liste de produits
		listeProduits = clientService.getProductByKeyWordService("");
		
		//Retour à la page qui a appelé la méthode
		return null;
	}

	//Suppression du produit
	public String deleteProduct() {
		
		//Suppression du panier avec l'index du tableau
		this.listeLignesCmd.remove(indexrow);

		//Retour à la page ayant appelé la méthode
		return null;
	}
}

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

	// Injection des variable d'acc�s au back-end dans le contener web qui va
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

	// Listes utilis�es dans les menus d�roulants
	List<Categorie> listeCategories;
	List<Produit> listeProduits;
	// Listes utilis�es pour les liens entre les tables de la BDD
	List<LigneCommande> listeLignesCmd;
	List<Produit> listeProduitsCmd;
	List<Commande> listeCommande;

	// Index des cat�gories dans les menus d�roulant
	String indexCat;
	// mot clef recherch�
	String recherche = "";
	// Num�ro de la ligne dans les tableaux de produit
	int indexrow;

	// Constructeur vide => instanciation (et initialisation)
	public ClientManagedBean() {
		// Instanciation
		client = new Client();
		produit = new Produit();
		categorie = new Categorie();
		ligneCommande = new LigneCommande();

		// Cr�ation d'une nouvelle commande
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

	// M�thodes

	// M�thode execut�e � la construction de la page
	@PostConstruct
	public void getAllCategories() {

		// Initialisation de la liste de toutes les cat�gories
		this.listeCategories = clientService.getAllCategories();

		// Initialisation de la liste des produits poss�dant le mot clef saisi
		// dans la barre de recherche
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);

	}

	// R�cup�re la liste de tous les produits
	public void getAllProd() {
		this.listeProduits = clientService.getProductByKeyWordService("");
	}

	// R�cup�re la liste des produits poss�dant le mot clef saisi dans la barre
	// de recherche
	public void getProd() {
		System.out.println("=================+>" + this.listeProduits);
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);
		System.out.println("=================+>" + this.listeProduits);

	}

	// R�cup�re le contenu du panier
	public void getCart() {

		// R�cup�re la liste des lignes de commandes
		List<LigneCommande> llcmd = this.commande.getListeLignesCommandes();

		// R�initialisation de la liste des produits (qui s'affiche dans le
		// tableau
		this.listeProduits.clear();

		// On met les produits r�cup�r�s dans la liste des lignes de commande
		// dans la liste de produits � afficher
		for (LigneCommande lc : llcmd) {
			this.listeProduits.add(lc.getProduit());
		}
	}

	// R�cup�re la liste des produits correspondants � la cat�gorie s�lectionn�e
	public String getProdByCat() {
		this.listeProduits = clientService.getProductByCatService(Integer.parseInt(this.indexCat));
		return null;
	}

	// R�cup�re la liste des produits contenant le mot saisi dans la barre de
	// recherche
	public String getByKeyWord() {
		this.listeProduits = clientService.getProductByKeyWordService(this.recherche);

		// Retour � la page de recherhce pour afficher le r�sultat
		return "Recherche";
	}

	// M�thode pour enregistrer la commande
	public String order() {

		// On associe le client � la commande
		this.commande.setClient(this.client);

		// Cr�ation de la liste des commandes pass�es par le client
		List<Commande> listCmd = new ArrayList<Commande>();
		listCmd = this.client.getListeCommande();

		// Ajout de la commande pass�e dans la liste de toutes les commandes
		listCmd.add(this.commande);

		// Association du client et des commandes pass�es
		this.client.setListeCommande(listCmd);

		// Enregistrement de la commande dans la BDD
		clientService.orderService(client);

		// d�biter les stocks pour chacun des produits command�s
		for (LigneCommande lc : this.listeLignesCmd) {

			// On r�cup�re un produit de la liste des lignes de commande
			Produit p = new Produit();
			p = lc.getProduit();

			// Onr�cup�re la quantit� du produit achet� par le client
			int quantt = p.getQuantite();

			// On r�cup�re la quantit� de produits disponible dans la BDD
			int quanttStock = clientService.getProductByIdService(p).get(0).getQuantite();

			// Nombre de produits restants apr�s l'achat
			p.setQuantite(quanttStock - quantt);

			// Mise � jour du produit dans la BDD apr�s achat
			int verif = adminService.updateProductService(p);

		}

		// R�initialisation des variables apr�s les variables (m�me chose que
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

		//On met la liste de tous les produits dans la liste � afficher
		this.listeProduits = clientService.getProductByKeyWordService("");

		//Retour � l'accueil
		return "Accueil";
	}

	//Ajout du produit dans le panier
	public String selectProduct() {

		// Ajout du produit & de la commande dans la ligne commande
		LigneCommande lc = new LigneCommande();
		lc.setProduit(this.produit);
		lc.setCommande(this.commande);
		
		//On r�cup�re la quantit� et le prix et on calcule le prix total concernant ce produit
		int quantite = this.produit.getQuantite();
		double prix = quantite * this.produit.getPrix();
		
		//On met la quantit� et le prix calcul� dans la ligne de commande
		lc.setPrix(prix);
		lc.setQuantite(quantite);

		//On r�cup�re la liste des lignes de commande (tous les produits du panier)
		List<LigneCommande> llcmd = new ArrayList<LigneCommande>();
		llcmd = this.produit.getListeLigneCommandes();
		//Ajout de la nouvelle ligne de commande
		llcmd.add(lc);
		// Ajout de la ligne commande dans le produit
		this.produit.setListeLigneCommandes(llcmd);

		//On r�cup�re la liste de ligne de commande dans la commande actuelle
		llcmd = this.commande.getListeLignesCommandes();
		//Ajout de la ligne de commande dans la liste
		llcmd.add(lc);
		// Ajout de la liste de ligne commande dans la commande
		this.commande.setListeLignesCommandes(llcmd);

		// Le produit est s�lectionn�
		this.produit.setSelectionne(true);

		// Regeneration de la liste de produits
		listeProduits = clientService.getProductByKeyWordService("");
		
		//Retour � la page qui a appel� la m�thode
		return null;
	}

	//Suppression du produit
	public String deleteProduct() {
		
		//Suppression du panier avec l'index du tableau
		this.listeLignesCmd.remove(indexrow);

		//Retour � la page ayant appel� la m�thode
		return null;
	}
}

package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.IClientService;

@ManagedBean(name = "adminMB")
@SessionScoped
public class AdminManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributs
	AdminProd administrateur;

	// Injection des varaibles d'impl�mentation dans le conteneur web
	@EJB
	IAdministrateurService adminService;
	@EJB
	IClientService clientService;

	// Variables
	
	//produit (pour mise � jour, cr�ation d'un produit, etc.)
	Produit produit;
	//Cat�gorie (idem que pour le produit)
	Categorie categorie;
	//Liste de toutes les cat�gories
	List<Categorie> listeCategories;
	//Liste de tous les produits
	List<Produit> listeProduits;
	//r�sultat = 1 => requ�te execut�e par le SGBD
	int resultat;
	//index quelconque selectionn� dans un menu d�roulant (string)
	String indexSelect;
	//index du produit s�lectionn� dans un menu d�roulant 
	String indexSelectId;
	//Index inutile car n'est pas utilis� ...
	long indexUpdate;
	//Produit pour la mise � jour (pas tr�s utile non plus)
	Produit produitUpdate;

	// Constructeur vide
	public AdminManagedBean() {
		// Initialisation des variables dans le constructeur vide pour les
		// initialiser lors du premier appel de la ManagedBean
		this.administrateur = new AdminProd();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.listeCategories = new ArrayList<Categorie>();
		this.indexSelectId = "0";
	}

	// Getters & Setters
	public AdminProd getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(AdminProd administrateur) {
		this.administrateur = administrateur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getResultat() {
		return resultat;
	}

	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getIndexSelect() {
		return indexSelect;
	}

	public void setIndexSelect(String indexSelect) {
		this.indexSelect = indexSelect;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public String getIndexSelectId() {
		return indexSelectId;
	}

	public void setIndexSelectId(String indexSelectId) {
		this.indexSelectId = indexSelectId;
	}

	public long getIndexUpdate() {
		return indexUpdate;
	}

	public void setIndexUpdate(long indexUpdate) {
		this.indexUpdate = indexUpdate;
	}

	// M�thodes

	// M�thode appel�e lors de la construction de la page web (premier appel)
	@PostConstruct
	public void getAllPostConstr() {

		// Initialise la liste des cat�gories avec toutes les cat�gories
		this.listeCategories = clientService.getAllCategories();

		// Initialise la liste des produits avec tous les produits
		this.listeProduits = adminService.getAllProductService();

		// Initialise le produit courant avec l'id du produit s�lectionn�
		this.produit = listeProduits.get(Integer.parseInt(indexSelectId));
	}

	// Ajout des produits
	public String addProduct() {

		// R�cup�re la cat�gorie s�lectionn�e dans le menu d�roulant
		// indexSelect => index de la liste d�roulante
		// listeCategorie => liste des cat�gories r�cup�r�e dans le
		// postConstruct
		Categorie cat = new Categorie();
		cat = this.listeCategories.get(Integer.parseInt(this.indexSelect) - 1);

		// Cr�ation d'un nouveau produit avec le nouveau produit saisi dans le
		// formulaire
		Produit p = new Produit(this.produit.getDesignation(), this.produit.getDescription(), this.produit.getPrix(),
				this.produit.getQuantite(), false);

		// Ajout de la cat�gorie s�lectionn�e dans le menu d�roulant
		p.setCategorie(cat);

		// Ajout du produit dans la BDD
		this.resultat = adminService.addProductService(p);

		// R�cup�ration de la liste de tous les produits
		List<Produit> lp = new ArrayList<Produit>();
		lp = adminService.getAllProductService();

		// Ne sert � rien
		p = lp.get(lp.size() - 1);
		adminService.updateProductService(p);

		// REtour � l'accueil
		return "Accueil";
	}

	// Supprimer un produit
	public String delProduct() {
		// Suppression du produit avec son index
		this.resultat = adminService.delProductService(Long.parseLong(indexSelectId));
		// Retour � l'accueil
		return "Accueil";
	}

	// Mise � jour d'un produit
	public String updateProduct() {
		// R�cup�ration du context
		FacesContext fc = FacesContext.getCurrentInstance();
		// R�cup�ration de la session � partir du context
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));

		// R�cup�ration du produit � mettre � jour
		produitUpdate = (Produit) httpSession.getAttribute("produitUpdate");

		// Modification du produit avec les les valeurs saisies dans le
		// formulaire
		this.produitUpdate.setDesignation(this.produit.getDesignation());
		this.produitUpdate.setDescription(this.produit.getDescription());
		this.produitUpdate.setPrix(this.produit.getPrix());
		this.produitUpdate.setQuantite(this.produit.getQuantite());
		this.produitUpdate.setCategorie(this.categorie);

		// Envoi du produit modifi� � la BDD
		this.resultat = adminService.updateProductService(this.produitUpdate);

		// On met le produit modifi� � jour dans la variable d'acces
		this.produit = this.produitUpdate;

		// Retour � l'accueil
		return "Accueil";
	}

	// Trouver un produit avec son index
	public String findProduct() {
		// Appel de la m�thode getById avec l'index s�lectionn� dans le
		// formulaire
		this.produit = adminService.getByIdProductService(Long.parseLong(indexSelectId));

		// retour � la page qui a appel� la m�thode
		return null;
	}

	// M�thode pour remplir le formulaire (de modification d'un produit) avec le
	// produit correspondant � l'index s�lectionn�
	public void refreshOneProduct(ValueChangeEvent event) {
		// Conversion de l'index s�lectionn� (via un �venement et un listenner)
		// en Long
		long newId = Long.parseLong((String) event.getNewValue());

		// R�cup�re le produit correspondant � l'index
		this.produit = adminService.getByIdProductService(newId);

		// On met le produit r�cup�r� dans la variable accessible (produit �
		// modifi�)
		this.produitUpdate = new Produit(this.produit.getIdProduit(), this.produit.getDesignation(),
				this.produit.getDescription(), this.produit.getPrix(), this.produit.getQuantite(), false);

		// On met le produit dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitUpdate", produitUpdate);
	}

	// Mise � jour de la variable cat�gorie (accessible) � partir de l'index de
	// la liste d�roulante
	public void refreshOneCategorie(ValueChangeEvent event) {

		this.categorie = this.listeCategories.get(Integer.parseInt((String) event.getNewValue()) - 1);
	}

	// M�thode de v�rification de la connexion
	public String connexion() {

		// V�rification que le couple login et mot de passe existe dans la BDD
		this.administrateur = adminService.isExistService(this.administrateur);

		// Si l'identification est bonne
		if (administrateur != null) {
			// Cr�er une session et ajouter l'agent � celle-ci
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agentSession", administrateur);

			// Envoi vers la page d'accueil administrateur
			return "ConnectSuccess";
		} else { // Si l'identification est fausse
			// Ajout du message d�reur dans le context
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Les informations saisies sont �ronn�es"));

			// retour dans la page de login
			return "ConnectFail";
		}
	}

	// M�thode de d�connexion
	public String deconnexion() {

		// On d�truit la session
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		// Retour � la page de login
		return "/Admin/login.xhtml";
	}

	// M�thode pour v�rifier que l'utilisateur est bien connect� avant
	// d'afficher la page
	//Cette m�thode est appell�e par le header administrateur
	public void checkPermissions(ComponentSystemEvent event) throws IOException {

		// R�cup�re le contexte
		FacesContext fc = FacesContext.getCurrentInstance();

		// R�cup�re la session (n'en cr�e pas une nouvelle si elle n'existe pas)
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));

		// Cr�ation d'une variable administrateur
		AdminProd adminSession = null;

		// Si la session existe
		if (httpSession != null) {
			// On met les infos administrateur dans la variable administrateur
			adminSession = (AdminProd) httpSession.getAttribute("agentSession");
		}

		// Si la session n'existe pas
		if (adminSession == null) {

			// Ajout d'un message dans le context
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous devez vous connecter pour acc�der � l'espace administrateur"));

			// Redirection vers la page de login
			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/Admin/login.xhtml");
		}
	}

	// Verification de la connexion dans la page de login uniquement
	public void checkPermissionslogin(ComponentSystemEvent event) throws IOException {

		// R�cup�re le contexte
		FacesContext fc = FacesContext.getCurrentInstance();

		// R�cup�re la session
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));

		// Cr�ation d'une variable administrateur
		AdminProd adminSession = null;

		// Si la session existe
		if (httpSession != null) {
			// On met les donn�es de session de l'admin dans la variable
			// administrateur
			adminSession = (AdminProd) httpSession.getAttribute("agentSession");
		}

		// Si la session existe d�j�, pas besoin de se logger donc redirection
		// sur l'accueil
		if (adminSession != null) {
			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/Admin/accueilAdmin.xhtml");
		}
	}
}

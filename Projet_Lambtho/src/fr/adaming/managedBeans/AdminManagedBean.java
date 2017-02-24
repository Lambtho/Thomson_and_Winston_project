package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.IClientService;

@ManagedBean(name = "adminMB")
public class AdminManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	AdminProd administrateur;

	@EJB
	IAdministrateurService adminService;

	@EJB
	IClientService clientService;

	Produit produit;
	Categorie categorie;
	List<Categorie> listeCategories;
	List<Produit> listeProduits;
	int resultat;
	String indexSelect;

	public AdminManagedBean() {
		this.administrateur = new AdminProd();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.listeCategories = new ArrayList<>();
	}

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

	// Méthodes
	@PostConstruct
	public void getAllPostConstr() {
		// this.listeCategories = clientService.getAllCategories();
		Categorie cat = new Categorie(1, "ma categorie", "ceci est la description de la catégorie");
		this.listeCategories.add(cat);
		cat = new Categorie(2, "ma categorie 2", "ceci est la description de la catégorie");
		this.listeCategories.add(cat);
		
		this.listeProduits = adminService.getAllProductService();
	}


	public String addProduct() {
//		this.produit.setCategorie(this.listeCategories.get(Integer.parseInt(this.indexSelect)-1));
		this.resultat = adminService.addProductService(this.produit);
		return "Accueil";
	}

	public String delProduct() {
		this.resultat = adminService.delProductService(Integer.parseInt(indexSelect));
		return "Accueil";
	}
	
	public String updateProduct() {
		this.resultat = adminService.updateProductService(this.produit);
		return "Accueil";
	}

	public String connexion() {

		// this.administrateur =
		// adminService.isExistService(this.administrateur);
		this.administrateur = new AdminProd("Thovex Candide", "candide@thovex", "ct");
		if (administrateur != null) {
			// Créer une session et ajouter l'agent à celle-ci
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agentSession", administrateur);
			return "ConnectSuccess";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Les informations saisies sont éronnées"));
			return "ConnectFail";
		}
	}

	public String deconnexion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Admin/login.xhtml";
	}

	public void checkPermissions(ComponentSystemEvent event) throws IOException {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));
		AdminProd adminSession = null;
		if (httpSession != null) {
			adminSession = (AdminProd) httpSession.getAttribute("agentSession");
		}
		if (adminSession == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous devez vous connecter pour accéder à l'espace administrateur"));

			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/Admin/login.xhtml");
		}
	}

	public void checkPermissionslogin(ComponentSystemEvent event) throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) (fc.getExternalContext().getSession(false));
		AdminProd adminSession = null;
		if (httpSession != null) {
			adminSession = (AdminProd) httpSession.getAttribute("agentSession");
		}
		if (adminSession != null) {
			ExternalContext ec = fc.getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/Admin/accueilAdmin.xhtml");
		}
	}
}

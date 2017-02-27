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
	String indexSelectId;
	long indexUpdate;

	public AdminManagedBean() {
		this.administrateur = new AdminProd();
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.listeCategories = new ArrayList<>();
		this.indexSelectId = "0";
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

	// Méthodes
	@PostConstruct
	public void getAllPostConstr() {
		this.listeCategories = clientService.getAllCategories();
		this.listeProduits = adminService.getAllProductService();
		this.produit = listeProduits.get(Integer.parseInt(indexSelectId));
	}

	public String addProduct() {

		Categorie cat = new Categorie();
		cat = this.listeCategories.get(Integer.parseInt(this.indexSelect) - 1);

		Produit p = new Produit(this.produit.getDesignation(), this.produit.getDescription(), this.produit.getPrix(),
				this.produit.getQuantite(), false);
		p.setCategorie(cat);

		this.resultat = adminService.addProductService(p);
		List<Produit> lp = new ArrayList<Produit>();
		lp = adminService.getAllProductService();
		p = lp.get(lp.size() - 1);

		adminService.updateProductService(p);

		return "Accueil";
	}

	public String delProduct() {
		this.resultat = adminService.delProductService(Long.parseLong(indexSelectId));
		return "Accueil";
	}

	public String updateProduct() {
		System.out.println(
				"abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + this.produit.getIdProduit());

		this.produit.setCategorie(this.categorie);
		this.resultat = adminService.updateProductService(this.produit);
		return "Accueil";
	}

	public String findProduct() {
		this.produit = adminService.getByIdProductService(Long.parseLong(indexSelectId));
		return null;
	}

	public void refreshOneProduct(ValueChangeEvent event) {

		long newId = Long.parseLong((String) event.getNewValue());
		this.produit = adminService.getByIdProductService(newId);
		this.produit.getIdProduit();
	}

	public void refreshOneCategorie(ValueChangeEvent event) {

		this.categorie = this.listeCategories.get(Integer.parseInt((String) event.getNewValue()) - 1);
	}

	public String connexion() {

		this.administrateur = adminService.isExistService(this.administrateur);
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

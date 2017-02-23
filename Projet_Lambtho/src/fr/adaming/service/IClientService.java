package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.Produit;

@Local
public interface IClientService {

	
	public List<Categorie> getAllCategories();
	
	public List<Produit> getProductByCatService(int id_cat);
	
	public List<Produit> getProductByPanierService(List<Integer> listePanier);
	
	public List<Produit> getProductByKeyWordService (String keyWord);
	
	public int  orderService(Client client, Commande commande);

}

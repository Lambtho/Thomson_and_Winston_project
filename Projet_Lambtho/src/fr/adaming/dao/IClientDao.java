package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.Produit;

@Local
public interface IClientDao {
	
	public List<Categorie> getAllCategories();
	
	public List<Produit> getProductByCat(int id_cat);
	
	public List<Produit> getProductByPanier(List<Integer> listePanier);
	
	public List<Produit> getProductByKeyWord (String keyWord);
	
	public int  order(Client client, Commande commande);

}

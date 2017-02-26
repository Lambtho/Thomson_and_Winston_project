package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

@Local
public interface IClientService {

	/**
	 * Récupérer la liste des catégories de produits 
	 * @return listCat
	 * 			La liste de catégorie de produits
	 * 
	 * @author Thomas et Lambert
	 * @see ClientServiceImpl#getAllCategories()
	 *
	 */
	public List<Categorie> getAllCategories();
	
	/**
	 * Récupérer les produits d'une même catégorie, 
	 * à l'aide de l'identifiant de la catégorie.
	 * @param id_cat
	 * 			l'identifiant de la catégorie 
	 * @return listProd
	 * 			la liste de produits associé à la catégorie
	 * 
	 * @author Thomas et Lambert
	 * @see ClientServiceImpl#getProductByCatService(int)
	 */
	public List<Produit> getProductByCatService(int id_cat);
	
	
	/**
	 * Récupérer les produits, 
	 * à l'aide de l'identifiant du produit.
	 * @param produit
	 * 			l'objet produit 
	 * @return listProdId
	 * 			la liste des produits possédant l'id correspondante
	 * 
	 * @author Thomas et Lambert
	 * @see ClientServiceImpl#getProductByIdService(Produit)
	 */
	public List<Produit> getProductByIdService(Produit produit);
	
	
	/**
	 * Récupérer les produits, 
	 * à l'aide de mots clés.
	 * @param keyWord
	 * 			Le mot clé, chaine de caractère présent dans la désigantion du produit 
	 * @return listProd
	 * 			la liste des produits possédant la chaine de caractère correspondante
	 * 
	 * @author Thomas et Lambert
	 * @see ClientServiceImpl#getProductByKeyWordService(String)
	 */
	public List<Produit> getProductByKeyWordService (String keyWord);
	
	/**
	 * Enregistrer un Client dans la base de données, 
	 * 
	 * @param client
	 * 			l'objet Client
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see ClientServiceImpl#orderService(Client)
	 */
	public int  orderService(Client client);

}

package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.Produit;

/**
 * @see IClientDao
 * @author Thomas et Lambert
 *
 */


@Local
public interface IClientDao {
	
	/**
	 * R�cup�rer la liste des cat�gories de produits 
	 * @return listCat
	 * 			La liste de cat�gorie de produits
	 * 
	 * @author Thomas et Lambert
	 * @see ClientDaoImpl#getAllCategories()
	 *
	 */
	
	public List<Categorie> getAllCategories();
	
	
	
	/**
	 * R�cup�rer les produits d'une m�me cat�gorie, 
	 * � l'aide de l'identifiant de la cat�gorie.
	 * @param id_cat
	 * 			l'identifiant de la cat�gorie 
	 * @return listProd
	 * 			la liste de produits associ� � la cat�gorie
	 * 
	 * @author Thomas et Lambert
	 * @see ClientDaoImpl#getProductByCat(int)
	 */
	
	public List<Produit> getProductByCat(int id_cat);
	
	
	
	/**
	 * R�cup�rer les produits, 
	 * � l'aide de l'identifiant du produit.
	 * @param produit
	 * 			l'objet produit 
	 * @return listProdId
	 * 			la liste des produits poss�dant l'id correspondante
	 * 
	 * @author Thomas et Lambert
	 * @see ClientDaoImpl#getProductById(Produit)
	 */
	
	public List<Produit> getProductById(Produit produit);
	
	
	
	/**
	 * R�cup�rer les produits, 
	 * � l'aide de mots cl�s.
	 * @param keyWord
	 * 			Le mot cl�, chaine de caract�re pr�sent dans la d�sigantion du produit 
	 * @return listProd
	 * 			la liste des produits poss�dant la chaine de caract�re correspondante
	 * 
	 * @author Thomas et Lambert
	 * @see ClientDaoImpl#getProductByKeyWord(String)
	 */
	public List<Produit> getProductByKeyWord (String keyWord);
	
	
	/**
	 * Enregistrer un Client dans la base de donn�es, 
	 * 
	 * @param client
	 * 			l'objet Client
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see ClientDaoImpl#order(Client)
	 */
	public int  order(Client client);

}

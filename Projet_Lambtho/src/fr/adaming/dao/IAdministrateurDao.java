package fr.adaming.dao;


import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;



@Local
public interface IAdministrateurDao {
	
	/**
	 * Pour les attribut email et password, verifier si ils 
	 * sont associés à un AdminProd
	 * @param admin
	 * 			l'AdminProd sur lequel ont effectue les verifications
	 * @return adminRetour
	 * 			L'AdminProd dont les attributs email et password sont verifiés
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#isExist(AdminProd)
	 *
	 */
	
	public AdminProd isExist(AdminProd admin);
	
	
	/**
	 * Ajouter un Produit à la base de données
	 * @param prod
	 * 			Le produit ajouter à la base de données
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#addProduct(Produit)
	 *
	 */
	public int addProduct(Produit prod);
	
	/**
	 * Supprimer un Produit de la base de données, à l'aide de son identifiant
	 * @param id_prod
	 * 			l'id du produit à supprimer
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#delProduct(long)
	 *
	 */
	public int delProduct(long id_prod);
	
	
	/**
	 * Modifier un Produit de la base de données
	 * @param prod
	 * 			le produit modifié
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#updateProduct(Produit)
	 *
	 */
	public int updateProduct (Produit prod);
	
	
	/**
	 * Récupérer la liste des produits 
	 * @return listProd
	 * 			La liste des produits
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#getAllProduct()
	 *
	 */
	public List<Produit> getAllProduct ();
	
	
	/**
	 * Récupérer les produits, 
	 * à l'aide de l'identifiant du produit.
	 * @param id_prod
	 * 			l'identifiant de l'objet Produit
	 * @return pr1
	 * 			l'objet Produit
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#getByIdProduct(long)
	 */
	public Produit getByIdProduct(long id_prod);
	
	
}

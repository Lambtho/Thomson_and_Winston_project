package fr.adaming.dao;


import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;



@Local
public interface IAdministrateurDao {
	
	/**
	 * Pour les attribut email et password, verifier si ils 
	 * sont associ�s � un AdminProd
	 * @param admin
	 * 			l'AdminProd sur lequel ont effectue les verifications
	 * @return adminRetour
	 * 			L'AdminProd dont les attributs email et password sont verifi�s
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#isExist(AdminProd)
	 *
	 */
	
	public AdminProd isExist(AdminProd admin);
	
	
	/**
	 * Ajouter un Produit � la base de donn�es
	 * @param prod
	 * 			Le produit ajouter � la base de donn�es
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#addProduct(Produit)
	 *
	 */
	public int addProduct(Produit prod);
	
	/**
	 * Supprimer un Produit de la base de donn�es, � l'aide de son identifiant
	 * @param id_prod
	 * 			l'id du produit � supprimer
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#delProduct(long)
	 *
	 */
	public int delProduct(long id_prod);
	
	
	/**
	 * Modifier un Produit de la base de donn�es
	 * @param prod
	 * 			le produit modifi�
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#updateProduct(Produit)
	 *
	 */
	public int updateProduct (Produit prod);
	
	
	/**
	 * R�cup�rer la liste des produits 
	 * @return listProd
	 * 			La liste des produits
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurDaoImpl#getAllProduct()
	 *
	 */
	public List<Produit> getAllProduct ();
	
	
	/**
	 * R�cup�rer les produits, 
	 * � l'aide de l'identifiant du produit.
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

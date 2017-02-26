package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.dao.AdministrateurDaoImpl;
import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;

@Local
public interface IAdministrateurService {
	
	
	/**
	 * Pour les attribut email et password, verifier si ils 
	 * sont associ�s � un AdminProd
	 * @param admin
	 * 			l'AdminProd sur lequel ont effectue les verifications
	 * @return adminRetour
	 * 			L'AdminProd dont les attributs email et password sont verifi�s
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#isExistService(AdminProd)
	 *
	 */
	public AdminProd isExistService(AdminProd admin);
	
	/**
	 * Ajouter un Produit � la base de donn�es
	 * @param prod
	 * 			Le produit ajouter � la base de donn�es
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#addProductService(Produit)
	 *
	 */
	public int addProductService(Produit prod);
	

	/**
	 * Supprimer un Produit de la base de donn�es, � l'aide de son identifiant
	 * @param id_prod
	 * 			l'id du produit � supprimer
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#delProductService(long)
	 *
	 */
	public int delProductService(long id_prod);
	

	/**
	 * Modifier un Produit de la base de donn�es
	 * @param prod
	 * 			le produit modifi�
	 * @return verif
	 * 			l'entier qui atteste du bon d�roulement de la requ�te.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#updateProductService(Produit)
	 *
	 */
	public int updateProductService(Produit prod);
	

	/**
	 * R�cup�rer la liste des produits 
	 * @return listProd
	 * 			La liste des produits
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#getAllProductService()
	 *
	 */
	public List<Produit> getAllProductService();
	
	

	/**
	 * R�cup�rer les produits, 
	 * � l'aide de l'identifiant du produit.
	 * @param id_prod
	 * 			l'identifiant de l'objet Produit
	 * @return pr1
	 * 			l'objet Produit
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#getByIdProductService(long)
	 */
	public Produit getByIdProductService(long id_prod);

}

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
	 * sont associés à un AdminProd
	 * @param admin
	 * 			l'AdminProd sur lequel ont effectue les verifications
	 * @return adminRetour
	 * 			L'AdminProd dont les attributs email et password sont verifiés
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#isExistService(AdminProd)
	 *
	 */
	public AdminProd isExistService(AdminProd admin);
	
	/**
	 * Ajouter un Produit à la base de données
	 * @param prod
	 * 			Le produit ajouter à la base de données
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#addProductService(Produit)
	 *
	 */
	public int addProductService(Produit prod);
	

	/**
	 * Supprimer un Produit de la base de données, à l'aide de son identifiant
	 * @param id_prod
	 * 			l'id du produit à supprimer
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#delProductService(long)
	 *
	 */
	public int delProductService(long id_prod);
	

	/**
	 * Modifier un Produit de la base de données
	 * @param prod
	 * 			le produit modifié
	 * @return verif
	 * 			l'entier qui atteste du bon déroulement de la requête.
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#updateProductService(Produit)
	 *
	 */
	public int updateProductService(Produit prod);
	

	/**
	 * Récupérer la liste des produits 
	 * @return listProd
	 * 			La liste des produits
	 * 
	 * @author Thomas et Lambert
	 * @see AdministrateurServiceImpl#getAllProductService()
	 *
	 */
	public List<Produit> getAllProductService();
	
	

	/**
	 * Récupérer les produits, 
	 * à l'aide de l'identifiant du produit.
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

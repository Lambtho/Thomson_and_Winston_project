package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;

/**
 * La Classe d'implémentation de {@link IAdministrateurDao}
 * @author Thomas et lambert
 *
 */

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@PersistenceContext(unitName = "PU_TP")
	EntityManager em;

	
	@Override
	public AdminProd isExist(AdminProd admin) {

		String req = "SELECT a FROM AdminProd a WHERE a.email=:pMail AND a.password=:pMdp";

		Query query = em.createQuery(req);

		query.setParameter("pMail", admin.getEmail());

		query.setParameter("pMdp", admin.getPassword());

		List<AdminProd> listeAdmin = query.getResultList();

		if (listeAdmin.size() != 0) {

			AdminProd adminRetour = listeAdmin.get(0);
			return adminRetour;
		} else {
			return null;
		}

	}
	
	
	@Override
	public int addProduct(Produit prod) {

		int verif = 1;

		try {

			em.persist(prod);

		} catch (PersistenceException e) {
			e.printStackTrace();

			verif = 0;
		}

		return verif;
	}
	

	@Override
	public int delProduct(long id_prod) {

		int verif = 1;

		try {

//			Produit pr1 = new Produit();
//			pr1.setIdProduit(id_prod);
			Produit pr1 = em.find(Produit.class, id_prod);

			em.remove(pr1);

		} catch (PersistenceException e) {
			e.printStackTrace();

			verif = 0;
		}

		return verif;
	}
	
	
	@Override
	public int updateProduct(Produit prod) {

		int verif = 1;

		try {

			Produit prd = em.find(Produit.class, prod.getIdProduit());

			prd.setDesignation(prod.getDesignation());
			prd.setDescription(prod.getDescription());
			prd.setPrix(prod.getPrix());
			prd.setQuantite(prod.getQuantite());
			prd.setSelectionne(prod.isSelectionne());

			em.merge(prd);

		} catch (Exception e) {

			e.printStackTrace();

			verif = 0;
		}

		return verif;
	}
	
	@Override
	public List<Produit> getAllProduct() {

		String req = "SELECT p FROM Produit p";

		Query q = em.createQuery(req);

		List<Produit> listProd = q.getResultList();

		System.out.println(listProd);

		if (listProd.size() != 0) {

			return listProd;

		} else {

			return null;
		}
	}
	
	

	@Override
	public Produit getByIdProduct(long id_prod) {

		Produit pr1 = em.find(Produit.class, id_prod);

		return pr1;
	}

}

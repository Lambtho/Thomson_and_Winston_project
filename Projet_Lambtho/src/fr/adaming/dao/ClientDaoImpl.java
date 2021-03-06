package fr.adaming.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

/**
 * La Classe d'impl�mentation de {@link IClientDao}
 * @author Thomas et lambert
 *
 */

@Stateless
public class ClientDaoImpl implements IClientDao {
	
	@PersistenceContext(unitName="PU_TP")
	EntityManager em;

	@Override
	public List<Categorie> getAllCategories() {

		String req = "SELECT c FROM Categorie c";

		Query q = em.createQuery(req);

		@SuppressWarnings("unchecked")
		List<Categorie> listCat = q.getResultList();

		System.out.println(listCat);

		if (listCat.size() != 0) {

			return listCat;

		} else {

			return null;
		}

	}

	
	@Override
	public List<Produit> getProductByCat(int id_cat) {
		String req = "SELECT * FROM produits as p LEFT JOIN categories as c ON c.id_cat= p.categorie_id_fk  WHERE id_cat =:pId_cat";

		Query query = em.createNativeQuery(req, Produit.class);
		query.setParameter("pId_cat", id_cat);

		@SuppressWarnings("unchecked")
		List<Produit> listeProd = query.getResultList();

		System.out.println("------- Liste des produits par cat�gories------------");

		for (Produit p : listeProd) {
			System.out.println(p);
		}
		return listeProd;
	}
	
	

	@Override
	public List<Produit> getProductById(Produit produit) {

		String req = "SELECT * FROM produits WHERE id_prd=?" ;

		Query query = em.createNativeQuery(req, Produit.class);
		query.setParameter(1, produit.getIdProduit());

		@SuppressWarnings("unchecked")
		List<Produit> listeProdId = query.getResultList();

		System.out.println("---- Liste des produits par Id------------");

		for (Produit p : listeProdId) {
			System.out.println(p);
		}

		return listeProdId;
	}

	
	@Override
	public List<Produit> getProductByKeyWord(String keyWord) {

		String sqlReq = "SELECT * FROM produits where  designation_prd like ? ";

		Query query = em.createNativeQuery(sqlReq, Produit.class);
		query.setParameter(1, "%"+keyWord+"%");

		@SuppressWarnings("unchecked")
		List<Produit> listeProd = query.getResultList();

		System.out.println("------- Liste des produits par mot cl�s------------");

		for (Produit p : listeProd) {
			System.out.println(p);
		}
		return listeProd;
	}
	
	

	@Override
	public int order(Client client) {
		
		int verif=1;
		
		try{
		
			em.persist(client);
			
			
		}catch (PersistenceException e){
			e.printStackTrace();
			
			verif =0;
		}
		return verif;
	}

}

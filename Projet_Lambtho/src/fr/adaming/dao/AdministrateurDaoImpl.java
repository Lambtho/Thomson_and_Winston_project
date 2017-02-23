package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {
	
	EntityManager em=null;

	@Override
	public AdminProd isExist(AdminProd admin) {
		
		String req = "SELECT a FROM Agent a WHERE a.mail=:pMail AND a.password=:pMdp" ;

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
		
		int verif=1;
		
		try{
			
			em.persist(prod);
		
		}catch (PersistenceException e){
			e.printStackTrace();
			
			verif =0;
		}
		
		return verif;
	}

	@Override
	public int delProduct(int id_prod) {
		
int verif=1;
		
		try{
			
		Produit pr1=em.find(Produit.class, id_prod);
			
		em.remove(pr1);
		
		}catch (PersistenceException e){
			e.printStackTrace();
			
			verif =0;
		}
		
		return verif;
	}
				

	@Override
	public int updateProduct(Produit prod) {
		
		Produit prd =em.find(Produit.class, prod.getIdProduit());
		
		prd.setDesignation(prod.getDesignation());
		prd.setDescription(prod.getDescription());
		
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setMail(user.getMail());
		u.setNaissance(user.getNaissance());
		System.out.println(u);
		em.merge(u);
		
		return 0;
	}

	@Override
	public List<Produit> getAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getByIdProduct(int id_prod) {
		// TODO Auto-generated method stub
		return null;
	}


	

	

}

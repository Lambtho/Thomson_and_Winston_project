package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.entities.Produit;

@Stateless
public class AdministrateurServiceImpl implements IAdministrateurService{

	// déclaration EJB Administrateurdao
	
	@EJB
	IAdministrateurDao adminDao;
	
	@Override
	public int addProductService(Produit prod) {
		
		return adminDao.addProduct(prod);
	}

	@Override
	public int delProductService(int id_prod) {
		
		return adminDao.delProduct(id_prod);
	}

	@Override
	public int updateProductService(Produit prod) {
		
		return adminDao.updateProduct(prod);
	}

	@Override
	public List<Produit> getAllProductService() {
		
		return adminDao.getAllProduct();
	}

	@Override
	public Produit getByIdProductService(int id_prod) {
		
		return adminDao.getByIdProduct(id_prod);
	}

}

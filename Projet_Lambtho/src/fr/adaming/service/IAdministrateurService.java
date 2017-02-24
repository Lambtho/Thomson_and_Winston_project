package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;

@Local
public interface IAdministrateurService {
	
	public AdminProd isExistService(AdminProd admin);
	
	public int addProductService(Produit prod);

	public int delProductService(long id_prod);

	public int updateProductService(Produit prod);

	public List<Produit> getAllProductService();

	public Produit getByIdProductService(long id_prod);

}

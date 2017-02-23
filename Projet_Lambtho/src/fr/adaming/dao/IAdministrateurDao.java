package fr.adaming.dao;


import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.AdminProd;
import fr.adaming.entities.Produit;

@Local
public interface IAdministrateurDao {
	
	public AdminProd isExist(AdminProd admin);
	
	public int addProduct(Produit prod);
	
	public int delProduct(int id_prod);
	
	public int updateProduct (Produit prod);
	
	public List<Produit> getAllProduct ();
	
	public Produit getByIdProduct(int id_prod);
	
	
}

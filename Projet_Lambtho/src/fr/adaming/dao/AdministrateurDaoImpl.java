package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;

import fr.adaming.entities.Produit;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@Override
	public int addProduct(Produit prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delProduct(int id_prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Produit prod) {
		// TODO Auto-generated method stub
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

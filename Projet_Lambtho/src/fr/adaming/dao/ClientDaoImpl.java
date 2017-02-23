package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Commande;
import fr.adaming.entities.Produit;

public class ClientDaoImpl implements IClientDao {

	@Override
	public List<Categorie> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductByCat(int id_cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductByPanier(List<Integer> listePanier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getProductByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int order(Client client, Commande commande) {
		// TODO Auto-generated method stub
		return 0;
	}

}

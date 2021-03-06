package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.IClientDao;
import fr.adaming.entities.Categorie;
import fr.adaming.entities.Client;
import fr.adaming.entities.Produit;

/**
 * La Classe d'implémentation de {@link IClientService}
 * @author Thomas et lambert
 *
 */
@Stateless
public class ClientServiceImpl implements IClientService {
	
	// Déclaration EJB clientDao
	@EJB
	IClientDao clientDao;

	@Override
	public List<Categorie> getAllCategories() {
		
		return clientDao.getAllCategories();
	}

	@Override
	public List<Produit> getProductByCatService(int id_cat) {
		
		return clientDao.getProductByCat(id_cat);
	}

	@Override
	public List<Produit> getProductByIdService(Produit produit) {
		
		return clientDao.getProductById(produit);
	}

	@Override
	public List<Produit> getProductByKeyWordService(String keyWord) {
		
		return clientDao.getProductByKeyWord(keyWord);
	}

	@Override
	public int orderService(Client client) {
		
		return clientDao.order(client);
	}

}

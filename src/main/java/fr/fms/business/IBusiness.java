package fr.fms.business;

import java.util.List;

import fr.fms.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IBusiness {
	/**
	 * Méthode qui renvoi la liste des articles en base
	 * @return List<Article> 
	 * @throws Exception
	 */
	public List<Article> getArticles() throws Exception;



	/**
	 * Méthode qui renvoi la liste des articles en base paginée
	 * @param kw est un mot dont on souhaite afficher tous les articles le contenant 
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Article>
	 * @throws Exception
	 */
	public Page<Cinema> getCinemaPages(String kw, int page) throws Exception;
	/**
	 * Méthode qui renvoi tous les articles d'une catégorie en base paginée
	 * @param idCat correspond à l'identifiant de la catégorie choisie
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Article>
	 * @throws Exception
	 */

	/**
	 * Méthode qui supprime un article à partir de son id
	 * @param id
	 * @throws Exception
	 */


	
	/**
	 * Méthode qui ajoute un article au panier
	 * @param article
	 */
	public void addArtToCart(Article article);	
	/**
	 * Méthode qui supprime un article du panier à partir d'un id (s'il existe)
	 * @param id
	 */
	public void delArtFromCart(Long id);		
	/**
	 * Méthode qui supprime le panier en cours
	 */
	public void delCart();						
	/**
	 * Méthode qui renvoi le contenu du panier
	 * @return List<Article>
	 */
	public List<Article> getCart();				
	/**
	 * Méthode qui renvoi le nombre d'éléments dans le panier en cours
	 * @return int
	 */
	public int getNbCart();

	public Page<Cinema> getCinemaByCityPage(Long idCat, int page) throws Exception;

	public Page <Movie> getMoviesByCinemaPage(Long idCiner , int page) throws Exception;

	public void deleteCinema(Long id) throws Exception;

	public void deleteMovie(Long id) throws Exception;

	public void deleteCity(Long id) throws Exception;

	/**
	 * Méthode qui renvoi un article en base à partir de son id
	 * @param id
	 * @return Article
	 * @throws Exception
	 */
	public Cinema getCinemaById(Long id) throws Exception;

	/**
	 * Méthode qui renvoi la liste des catégories en base
	 * @return
	 * @throws Exception
	 */
	public List<City> getCity() throws Exception;

	/**
	 * Méthode qui sauvegarde un article en base
	 * @param cinema
	 * @throws Exception
	 */
	public void saveCinema(Cinema cinema) throws Exception;
}

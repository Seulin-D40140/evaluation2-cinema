package fr.fms.business;

import java.util.List;
import java.util.Optional;

import fr.fms.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IBusiness {
	/**
	 * Méthode qui renvoi la liste des cinema en base paginée
	 * @param kw est un mot dont on souhaite afficher tous les cinema le contenant
	 * @param page correspond à la page active côté front, cela assure la pagination
	 * @return Page<Cinema>
	 * @throws Exception
	 */
	public Page<Cinema> getCinemaPages(String kw, int page) throws Exception;

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
	 * Méthode qui renvoi des cinema en base à partir de leur id
	 * @param id
	 * @return Article
	 * @throws Exception
	 */
	public Cinema getCinemaById(Long id) throws Exception;

	/**
	 * Méthode qui renvoi la liste des city en base
	 * @return
	 * @throws Exception
	 */
	public List<City> getCity() throws Exception;

	public List<Cinema> getCinema() throws Exception;

	/**
	 * Méthode qui sauvegarde un cinema en base
	 * @param cinema
	 * @throws Exception
	 */
	public void saveCinema(Cinema cinema) throws Exception;

	public void saveCity(City city) throws Exception;

	public void saveMovie(Movie movie) throws Exception;

	public Optional<Cinema> getCinemaByCityId(Long id);
}

package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;
import fr.fms.dao.*;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IBusinessImpl implements IBusiness {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CinemaRepository cinemaRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CityRepository cityRepository;
	
	private HashMap<Long, Article> cart;
	private Customer customer;
	
	public IBusinessImpl() {
		cart = new HashMap<>();
		customer = null;
	}

	@Override
	public void addArtToCart(Article article) {	
		Article a = cart.get(article.getId()); 
		if(a != null) {		
			a.setQuantity(a.getQuantity()+1);
		}
		else cart.put(article.getId(), article);		
	}

	@Override
	public void delArtFromCart(Long id) {
		cart.remove(id);		
	}

	@Override
	public void delCart() {
		cart.clear();		
	}

	@Override
	public List<Article> getCart() {
		return new ArrayList<>(cart.values());
	}

	public double getTotalAmount() {
		double total = 0;
		for(Article article : cart.values()) {
			total += article.getPrice()*article.getQuantity();
		}
		return total;
	}
	
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int getNbCart() {
		return cart.size();
	}
	
	@Override
	public List<Article> getArticles() throws Exception {
		return articleRepository.findAll();
	}

	public Category getCategoryById(Long id) throws Exception {
		return categoryRepository.getById(id);
	}

	public void order() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public String great() {
		return "Hello World";
	}

	@Override
	public Page<Cinema> getCinemaByCityPage(Long idCity, int page) throws Exception {
		return cinemaRepository.findByCityId(idCity, PageRequest.of(page, 5));
	}

	@Override
	public Page<Movie> getMoviesByCinemaPage(Long idCiner , int page ) throws Exception {
		return movieRepository.findByCinemaId(idCiner , PageRequest.of(page, 5));
	}

	@Override
	public Page<Cinema> getCinemaPages(String kw, int page) throws Exception {
		return cinemaRepository.findByNameContains(kw , PageRequest.of(page, 5));
	}

	@Transactional
	public void deleteCinema(Long id) throws Exception {
		cinemaRepository.deleteById(id);
	}

	@Transactional
	public void deleteCity(Long id) throws Exception {
		cityRepository.deleteById(id);
	}

	@Override
	public void deleteMovie(Long id) throws Exception {
		movieRepository.deleteById(id);
	}

	@Override
	public Cinema getCinemaById(Long id) throws Exception {
		Optional<Cinema> optional = cinemaRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	public List<City> getCity() throws Exception {
		return cityRepository.findAll();
	}

	@Override
	public void saveCinema(Cinema cinema) throws Exception {
		cinemaRepository.save(cinema);
	}

	@Override
	public void saveCity(City city) throws Exception {
		cityRepository.save(city);
	}

	@Override
	public void saveMovie(Movie movie) throws Exception {
		movieRepository.save(movie);
	}

	public List<Cinema> getCinema() throws Exception {
		return cinemaRepository.findAll();
	}
}

package fr.fms.business;

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
	CinemaRepository cinemaRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CityRepository cityRepository;
	

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	@Override
	public void delArtFromCart(Long id) {

	}

	@Override
	public void delCart() {

	}

	@Override
	public int getNbCart() {
		return 0;
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

	public Optional<Cinema> getCinemaByCityId(Long id)
	{
		return cinemaRepository.findById(id);
	}
}

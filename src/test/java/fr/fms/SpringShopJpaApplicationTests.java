package fr.fms;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SpringShopJpaApplicationTests {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CinemaRepository cinemaRepository;

	@Autowired
	MovieRepository movieRepository;

	@Test
	void testAddCity() throws Exception {
		City city = new City("toulouse", "48562");
		cityRepository.save(city);

		List<City> cityfind = cityRepository.findByNameContains("toulouse");

		assertEquals("toulouse" , cityfind.get(0).getName() );
	}

	@Test
	void testDeleteCity()
	{
		City city = new City("agen" , "47562");
		City citysavz = cityRepository.save(city);
		cityRepository.deleteById(citysavz.getId());
	}

	@Test
	void testAddCinema() throws Exception {
		City city = new City("agen" , "47562");
		Cinema ciner = new Cinema("toulouse", city);
		Cinema cinefind = cinemaRepository.save(ciner);

		Optional<Cinema> cinerfind = cinemaRepository.findById(cinefind.getId());
		assertTrue(cinerfind.isPresent());
	}

	@Test
	void testDeleteCinema()
	{
		City city = new City("agen" , "47562");
		Cinema ciner = new Cinema("toulouse", city);
		Cinema cinefind = cinemaRepository.save(ciner);
		cinemaRepository.deleteById(cinefind.getId());
	}

	@Test
	void testAddMovie() throws Exception {
		City city = new City("agen" , "47562");
		Cinema ciner = new Cinema("toulouse", city);
		Movie movie = new Movie("arcadie" , "woodland" , ciner);
		Movie moviefind = movieRepository.save(movie);

		Optional<Movie> moviesfind = movieRepository.findById(moviefind.getId());
		assertTrue(moviesfind.isPresent());
	}

	@Test
	void testDeleteMovie()
	{
		City city = new City("agen" , "47562");
		Cinema ciner = new Cinema("toulouse", city);
		Movie movie = new Movie("arcadie" , "woodland" , ciner);
		Movie moviefind = movieRepository.save(movie);
		movieRepository.deleteById(moviefind.getId());
	}

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}
}

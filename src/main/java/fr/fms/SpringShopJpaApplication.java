package fr.fms;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.fms.business.IBusinessImpl;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner
{
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CinemaRepository cinemaRepository;

	@Autowired
	IBusinessImpl businessImpl;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		generateDatas();
	}

	private void generateDatas()
	{
		//CITY DATA
		City soustons = cityRepository.save(new City("soustons" , "40140"));
		City bayonne = cityRepository.save(new City("bayonne" , "64240"));
		City dax = cityRepository.save(new City("dax" , "40200"));
		City anzin = cityRepository.save(new City("anzin" , "59410"));
		City tarnos = cityRepository.save(new City("tarnos" , "40520"));

		// CINEMA DATA
		Cinema ugc = cinemaRepository.save(new Cinema("ugc" ,soustons));
		Cinema grandrex = cinemaRepository.save(new Cinema("grand rex" , bayonne));
		Cinema pathe = cinemaRepository.save(new Cinema("pathe" , dax));
		Cinema oblivion = cinemaRepository.save(new Cinema("oblivion" , dax));
		Cinema ultraciner = cinemaRepository.save(new Cinema("ultraciner" , tarnos));
		Cinema gaumon = cinemaRepository.save(new Cinema("gaumon" , anzin));
		Cinema place = cinemaRepository.save(new Cinema("place ciner" ,soustons));
		Cinema pathoche = cinemaRepository.save(new Cinema("pathoche" , bayonne));
		Cinema gamon = cinemaRepository.save(new Cinema("gamon" , dax));
		Cinema ugc2 = cinemaRepository.save(new Cinema("ugc" , dax));
		Cinema pixar = cinemaRepository.save(new Cinema("pixar" , tarnos));
		Cinema disney = cinemaRepository.save(new Cinema("disney" , anzin));
		Cinema pixel = cinemaRepository.save(new Cinema("pixel" ,soustons));
		Cinema pixous = cinemaRepository.save(new Cinema("pixous" , bayonne));
		Cinema garmaron = cinemaRepository.save(new Cinema("garmaron" , dax));
		Cinema arcane = cinemaRepository.save(new Cinema("arcane" , dax));
		Cinema uhd = cinemaRepository.save(new Cinema("uhd" , tarnos));
		Cinema fhd = cinemaRepository.save(new Cinema("fhd" , anzin));

		//MOVIE DATA
		movieRepository.save(new Movie(null , "avengers" , "iron man", arcane));
		movieRepository.save(new Movie(null , "ultron age" , "blackwidows" , pixel));
		movieRepository.save(new Movie(null , "loki" , "thor" , pixar));
		movieRepository.save(new Movie(null , "fast X" , "fast and furious" , pixous));
		movieRepository.save(new Movie(null , "nightcore" , "horror movie" , arcane));
		movieRepository.save(new Movie(null , "2fast2furious" , "paul walker", ugc));
		movieRepository.save(new Movie(null , "inception" , "nolan" , grandrex));
		movieRepository.save(new Movie(null , "little bird" , "animation" , pathe));
		movieRepository.save(new Movie(null , "supergirl" , "series movies" , oblivion));
		movieRepository.save(new Movie(null , "walking dead" , "the movie" , ultraciner));
		movieRepository.save(new Movie(null , "fear walking dead" , "the movie", gaumon));
		movieRepository.save(new Movie(null , "deadpool" , "rayan reynonlds" , place));
		movieRepository.save(new Movie(null , "batman" , "darknight" , pathoche));
		movieRepository.save(new Movie(null , "wonderwoman" , "rise of queen" , gamon));
		movieRepository.save(new Movie(null , "tomb raider" , "life and death" , ugc2));
		movieRepository.save(new Movie(null , "volt" , "electric dog", disney));
		movieRepository.save(new Movie(null , "the 100" , "the movie" , garmaron));
		movieRepository.save(new Movie(null , "warzone" , "gear of war" , uhd));
		movieRepository.save(new Movie(null , "obviously" , "love movie" , fhd));
		movieRepository.save(new Movie(null , "midnightcore" , "horror movie" , pathoche));
	}
}

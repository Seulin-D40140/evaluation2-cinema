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

import java.util.Collection;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {
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
	public void run(String... args) throws Exception {
		generateDatas();
	}

	private void generateDatas() {
		//System.out.println(articleRepository.findById((long)1));
		//job.getArticles().stream().forEach(System.out::println);
//		articleRepository.findAll().forEach(a -> articleRepository.delete(a));
//		categoryRepository.findAll().forEach(c -> categoryRepository.delete(c));

		City soustons = cityRepository.save(new City("soustons" , "40140"));
		City bayonne = cityRepository.save(new City("bayonne" , "64240"));
		City dax = cityRepository.save(new City("dax" , "40200"));
		City anzin = cityRepository.save(new City("anzin" , "59410"));
		City tarnos = cityRepository.save(new City("tarnos" , "40520"));

		cinemaRepository.save(new Cinema(null, "ugc" ,soustons));
		cinemaRepository.save(new Cinema(null , "grand rex" , bayonne));
		cinemaRepository.save(new Cinema(null , "pathe" , dax));
		cinemaRepository.save(new Cinema(null , "oblivion" , dax));
		cinemaRepository.save(new Cinema(null , "ultraciner" , tarnos));
		cinemaRepository.save(new Cinema(null , "gaumon" , anzin));

//		movieRepository.save(new Movie(null , "avengers" , "iron man"));
//		movieRepository.save(new Movie(null , "ultron age" , "blackwidows"));
//		movieRepository.save(new Movie(null , "loki" , "thor"));
//		movieRepository.save(new Movie(null , "fast X" , "fast and furious"));
//		movieRepository.save(new Movie(null , "nightcore" , "horror movie"));
//		articleRepository.findAll().forEach(a -> System.out.println(a));
//		categoryRepository.findAll().forEach(c -> System.out.println(c));
		
//		for(Article article : articleRepository.findByBrand("Samsung")) {
//			System.out.println(article);
//		}
//		
//		System.out.println("------------------------------------------------");
//		
//		for(Article article : articleRepository.findByBrandContains("sung")) {
//			System.out.println(article);
//		}
//		
//		System.out.println("------------------------------------------------");
//		
//		for(Article article : articleRepository.findByBrandAndPrice("Samsung",250)) {
//			System.out.println(article);
//		}
//		
//		System.out.println("------------------------------------------------");
//		
//		for(Article article : articleRepository.findByBrandContainsAndPriceGreaterThan("sung",200)) {
//			System.out.println(article);
//		}

//		for(Article article : articleRepository.searchArticles("sung" , 200)) {
//			System.out.println(article);
//		}
//		
//		System.out.println("------------------------------------------------");
//		
//		for(Article article : articleRepository.findByCategoryId((long)1)) {
//			System.out.println(article);
//		}	
		
//		for(Article article : articleRepository.findByDescriptionAndBrand("S8", "Samsung")) {
//			System.out.println(article);
//		}
		
//		Optional<Article> article = articleRepository.findById((long)2);
//		article.get().setDescription("S9");		
//		articleRepository.save(article.get());
//		
//		System.out.println(articleRepository.findById((long)2));
//		System.out.println(articleRepository.findOne((long)2));
		
		//articleRepository.deleteById((long)23);
		//articleRepository.update(350,2);
	}
}

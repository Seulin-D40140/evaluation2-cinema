package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@SpringBootTest
class SpringShopJpaApplicationTests {
	@Autowired
	IBusinessImpl businessImpl;
	
	@Autowired
	ArticleRepository articleRepository;
	
	private static Instant startedAt;
	
	@BeforeEach
	public void beforeEachTest() {
		System.out.println("avant chaque test");
	}
	
	@AfterEach
	public void afterEachTest() {
		System.out.println("après chaque test");
	}
	
	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 1, 2, 42, 1011, 5089 })
	public void multiply_shouldReturnZero(int arg) {
		assertEquals(0, arg*0);
	}
	
	@Timeout(1)
	@Test
	public void orderShouldComputeLess1Second() {
		businessImpl.order();
	}
	
	@Test
	void testTotalAmontCart() {
		// Arrange
		businessImpl.addArtToCart(new Article((long)1,"Samsung","Samsung S8",250,1,null));
		businessImpl.addArtToCart(new Article((long)2,"Samsung","Samsung S9",250,1,null));
		businessImpl.addArtToCart(new Article((long)3,"IPhone","IPhone 10",500,1,null));
		
		// Act
		double amount = businessImpl.getTotalAmount(); 
		
		// Assert
		assertEquals(amount,1000);
	}
	
	@Test
	void changeQuantityWhenAddSameArticleToCart() {
		//GIVEN
		businessImpl.delCart();
		Article article = new Article((long)1,"Samsung","Samsung S8",250,1,null); 
		
		//WHEN
		businessImpl.addArtToCart(article);
		businessImpl.addArtToCart(article);
		businessImpl.addArtToCart(article);
		
		//THEN
		System.out.println("-------------->" + businessImpl.getNbCart());
		assertEquals(businessImpl.getNbCart(),1);
	}
}

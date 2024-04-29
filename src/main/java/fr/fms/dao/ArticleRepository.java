package fr.fms.dao;

import java.util.List;

import javax.transaction.Transactional;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ArticleRepository extends JpaRepository<Article, Long> {
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand, double price);
	public List<Article> findByBrandContainsAndPriceGreaterThan(String brand, double price);
	
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x")String brand,@Param("y")double price);
	
	@Query("select A from Article A where A.id= :id")
	public Article findOne(@Param("id")long id);
	
	@Modifying
	@Transactional
	@Query("update Article a set a.price= :p where a.id= :id")
	public void update(@Param("p")double price,@Param("id")long id);
	
	public List<Article> findByDescriptionAndBrand(String description, String brand);
	
	public List<Article> findByCategoryId(Long categoryId);
	
	public Page<Article> findAll(Pageable pageable);
	
	public Page<Article> findByDescriptionContains(String description , Pageable pageable);
	public Page<Article> findByCategoryId(Long categoryId , Pageable pageable);
}

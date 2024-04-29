package fr.fms.web;

import javax.validation.Valid;

import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.entities.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Article;
//import fr.fms.entities.Category;
import fr.fms.exceptions.ManageErrors;

@Controller
public class ArticleController {	
	@Autowired
	IBusinessImpl businessImpl;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	CinemaRepository cinemaRepository;
	
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	/**
	 * Méthode en GET correspondant à l'url .../index ou page d'accueil de notre application
	 * @param model sert à ajouter des éléments partagés avec la vue
	 * @param page correspond à la page active côté front, cela assure la pagination / par défaut vaut 0
	 * @param kw est un mot dont on souhaite afficher tous les articles le contenant / par défaut chaine vide
	 * @param idCat est l'identifiant de la catégorie dont on souhaite afficher tous les articles / par défaut vaut 0 
	 * @param cart correspond au nombre d'articles dans mon panier
	 * @param error contient l'élément positionné par redirectAttrs dans le cas d'une exception voir delete, save...
	 * @return la page articles.html 
	 */
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
									 @RequestParam(name="keyword" , defaultValue = "") String kw,
									 @RequestParam(name="idCity" , defaultValue = "0") Long idCity,
									 @RequestParam(name="nbcart" , defaultValue = "0") int cart,
									 @ModelAttribute(name="error") String error) {	
		Page<Cinema> cinema = null;
		model.addAttribute("error", model.getAttribute("error"));
		try {
			if(idCity > 0)	{
				cinema = businessImpl.getCinemaByCityPage(idCity,page);
				if(cinema.isEmpty())
					model.addAttribute("error", ManageErrors.STR_ERROR);
			}
			else cinema = businessImpl.getCinemaPages(kw,page);
						
			model.addAttribute("idCity",idCity);
			model.addAttribute("listCinema",cinema.getContent());
			model.addAttribute("pages", new int[cinema.getTotalPages()]);
			model.addAttribute("currentPage",page);
			model.addAttribute("keyword",kw);
			model.addAttribute("citys",cityRepository.findAll());
			model.addAttribute("nbcart", businessImpl.getNbCart());
			
			String username;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails)principal).getUsername();
			} else {
				username = principal.toString();
				if(username.contains("anonymous"))
					username = "";
			}
			model.addAttribute("username", " " +username);
		}
		catch(Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : INDEX] : {} " , e.getMessage());
		}		
		return "articles";
	}
	
	/**
	 * Méthode en GET correspondant à l'url .../delete consistant à supprimer un article à partir de son id
	 * @param id de l'article
	 * @param page
	 * @param keyword
	 * @param idCat
	 * @param redirectAttrs sert à injecter un paramètre dans la redirection, delors ../index pourra le récupérer via le modelAttribute
	 * @return une redirection vers ../index avec les éléments permettant de garder le contexte actuel
	 */
	@GetMapping("/delete")	
	public String delete(Long id, int page, String keyword , Long idCat, RedirectAttributes redirectAttrs) {
		//ToDo avant de supprimer un article, il faut supprimer les commandes qui y font références OrderItem/Order sans quoi Exception		
		try {
			businessImpl.deleteArticle(id);
		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : DELETE] : {} " , e.getMessage());
		}
		return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat;
	}
	/**
	 * Méthode en GET correspondant à l'url .../edit permettant d'afficher un article en vue de sa mise à jour
	 * @param id de l'article
	 * @param model
	 * @return page edit.html pour l'édition d'un article
	 */
	@GetMapping("/edit")	
	public String edit(Long id, Model model) {
		Article article;
		try {
			article = businessImpl.getArticleById(id);
			model.addAttribute("categories",businessImpl.getCatogries());
			model.addAttribute("article", article);
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : EDIT] : {} " , e.getMessage());
		}
		return "edit";
	}
	
	/**
	 * Méthode en GET correspondant à l'url .../article permettant d'ajouter un nouvel article
	 * @param model
	 * @return page article.html
	 */
	@GetMapping("/article")		
	public String article(Model model) {
		model.addAttribute("article" , new Article());
		try {
			model.addAttribute("categories",businessImpl.getCatogries());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : MANAGE NEW ARTICLE] : {} " , e.getMessage());
		}
		return "article";
	}
	
	/**
	 * Méthode en POST correspondant à l'url .../save visant à sauvegarder ou mettre à jour un article
	 * @param article
	 * @param bindingResult sert à la gestion des problèmes de saisie ne correspondant pas à ce qui est attendu / voir validation dans l'entité Article
	 * @param model
	 * @param redirectAttrs sert à ajouter un paramètre de redirection récupéré dans le modelAttribute afin d'afficher les erreurs suite à une exception
	 * @return redirection vers ../index
	 */
	@PostMapping("/save")		
	public String save(@Valid Article article, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("categories",businessImpl.getCatogries());
				return "article";
			}	
			businessImpl.saveArticle(article);		
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : SAVE ARTICLE] : {} " , e.getMessage());
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/greating")
	public @ResponseBody String greating() {
		return businessImpl.great();
	}	
}

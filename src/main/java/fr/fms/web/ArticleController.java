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
	
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);


	/**
	 * Méthode en GET correspondant à l'url .../edit permettant d'afficher un article en vue de sa mise à jour
	 * @param id de l'article
	 * @param model
	 * @return page edit.html pour l'édition d'un article
	 */
//	@GetMapping("/edit")
//	public String edit(Long id, Model model) {
//		Cinema article;
//		try {
//			article = businessImpl.getCinemaById(id);
//			model.addAttribute("categories",businessImpl.getCatogries());
//			model.addAttribute("article", article);
//		} catch (Exception e) {
//			model.addAttribute("error",e.getMessage());
//			logger.error("[ARTICLE CONTROLLER : EDIT] : {} " , e.getMessage());
//		}
//		return "edit";
//	}
	
	/**
	 * Méthode en GET correspondant à l'url .../article permettant d'ajouter un nouvel article
	 * @param model
	 * @return page article.html
	 */
	@GetMapping("/article")
	public String article(Model model) {
		model.addAttribute("cinema" , new Cinema());
		try {
			model.addAttribute("listCity",businessImpl.getCity());
		} catch (Exception e) {
			model.addAttribute("error",e.getMessage());
			logger.error("[ARTICLE CONTROLLER : MANAGE NEW ARTICLE] : {} " , e.getMessage());
		}
		return "article";
	}
	

	
	@RequestMapping("/greating")
	public @ResponseBody String greating() {
		return businessImpl.great();
	}	
}

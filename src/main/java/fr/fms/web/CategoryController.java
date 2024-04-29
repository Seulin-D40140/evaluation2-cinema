package fr.fms.web;

import java.util.Optional;

import fr.fms.dao.CinemaRepository;
import fr.fms.entities.Cinema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Category;
import fr.fms.exceptions.ManageErrors;

@Controller
public class CategoryController {
//	@Autowired
//	CategoryRepository categoryRepository;
//
//	@Autowired
//	CinemaRepository cinemaRepository;
//
//	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * Méthode qui contrôle si l'id de la catégorie existe avant de renvoyer vers l'affichage des articles par catégorie
	 * @param id de la catégorie
	 * @param model
	 * @return redirection vers ../index
	 */
//	@GetMapping("/category")
//	public String categories(Long id, Model model) {
//		Long idCity= (long)-1;
//		try {
//			Optional<Cinema> ciner = cinemaRepository.findById(id+1);
//			if(ciner.isPresent()) {
//				Cinema cinema = ciner.get();
//				idCity = cinema.getId();
//				model.addAttribute("idCity" , idCity);
//			}
//			else return "redirect:/index?error=" + ManageErrors.STR_ERROR;
//		}
//		catch(Exception e) {
//			logger.error("[CATEGORY CONTROLLER : CATEGORY] : {} " , e.getMessage());
//			return "redirect:/index?error=" + e.getMessage();
//		}
//		return "redirect:/index?idCity=" + idCity;
//	}
}

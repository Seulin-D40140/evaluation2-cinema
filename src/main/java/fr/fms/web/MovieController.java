package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.Movie;
import fr.fms.exceptions.ManageErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController
{
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    IBusinessImpl businessImpl;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movie")
    public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page,
                        @RequestParam(name="keyword" , defaultValue = "") String kw,
                        @RequestParam(name="idCiner" , defaultValue = "0") Long idCiner,
                        @RequestParam(name="nbcart" , defaultValue = "0") int cart,
                        @ModelAttribute(name="error") String error) {
        Page<Movie> movies = null;
        model.addAttribute("error", model.getAttribute("error"));
        try {
            if(idCiner > 0)	{
                movies = businessImpl.getMoviesByCinemaPage(idCiner , page);
                if(movies.isEmpty())
                    model.addAttribute("error", ManageErrors.STR_ERROR);
            }
            System.out.println(movies);
            model.addAttribute("idCiner",idCiner);
            model.addAttribute("listMovies",movies.getContent());
            model.addAttribute("keyword",kw);
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
        return "movies";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(Movie movie, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {

        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("listCity",businessImpl.getCity());
                return "addMovie";
            }
            businessImpl.saveMovie(movie);
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[ARTICLE CONTROLLER : SAVE ARTICLE] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }

    @GetMapping("/addMovie")
    public String Movie(Model model) {
        model.addAttribute("movie" , new Movie());
        try {
            model.addAttribute("listCinema",businessImpl.getCinema());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            logger.error("[ARTICLE CONTROLLER : MANAGE NEW ARTICLE] : {} " , e.getMessage());
        }
        return "addMovie";
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(Long id, RedirectAttributes redirectAttrs) {
        //ToDo avant de supprimer un article, il faut supprimer les commandes qui y font références OrderItem/Order sans quoi Exception
        try {
            businessImpl.deleteMovie(id);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[ARTICLE CONTROLLER : DELETE] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }
}

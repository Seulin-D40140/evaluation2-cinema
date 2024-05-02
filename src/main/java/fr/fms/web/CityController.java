package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CityRepository;
import fr.fms.entities.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CityController
{
    @Autowired
    IBusinessImpl businessImpl;

    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @PostMapping("/saveCity")
    public String save(City city, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {

        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("listCity",businessImpl.getCity());
                return "addCinema";
            }
            businessImpl.saveCity(city);
        }
        catch(Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[ARTICLE CONTROLLER : SAVE ARTICLE] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }

    @GetMapping("/gestionCity")
    public String City(Model model) throws Exception {
        model.addAttribute("city" , new City());
        model.addAttribute("listCity" , businessImpl.getCity());
        return "addCity";
    }

    @GetMapping("/deleteCity")
    public String deleteCity(Long id, RedirectAttributes redirectAttrs) {
        //ToDo avant de supprimer un article, il faut supprimer les commandes qui y font références OrderItem/Order sans quoi Exception
        try {
            businessImpl.deleteCity(id);
        } catch (Exception e) {
            redirectAttrs.addAttribute("error",e.getMessage());
            logger.error("[ARTICLE CONTROLLER : DELETE] : {} " , e.getMessage());
        }
        return "redirect:/index";
    }

}

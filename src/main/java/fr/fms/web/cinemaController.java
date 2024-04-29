package fr.fms.web;

import fr.fms.dao.CinemaRepository;
import fr.fms.entities.Cinema;
import fr.fms.exceptions.ManageErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class cinemaController
{
    @Autowired
    CinemaRepository cinemaRepository;

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/category")
    public String categories(Long id, Model model) {
        Long idCity= (long)-1;
        try {
            Optional<Cinema> ciner = cinemaRepository.findById(id+1);
            if(ciner.isPresent()) {
                Cinema cinema = ciner.get();
                idCity = cinema.getId();
                model.addAttribute("idCity" , idCity);
            }
            else return "redirect:/index?error=" + ManageErrors.STR_ERROR;
        }
        catch(Exception e) {
            logger.error("[CATEGORY CONTROLLER : CATEGORY] : {} " , e.getMessage());
            return "redirect:/index?error=" + e.getMessage();
        }
        return "redirect:/index?idCity=" + idCity;
    }
}

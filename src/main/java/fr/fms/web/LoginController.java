package fr.fms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * Méthode en GET /login pour la gestion du formulaire d'authentification géré ensuite par Spring security
	 * @return login.html
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Méthode en GET /logout pour la gestion de la déconnexion
	 * @param request
	 * @param response
	 * @return login.html
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login"; 
	}
	
	/**
	 * Méthode en GET pour l'url racine de l'application
	 * @return redirection vers index.html
	 */
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	/**
	 * Méthode en GET pour /403 pour interdire l'accès vers une ressource demandée
	 * @return 403.html
	 */
	@GetMapping("/403")
	public String accessDenied() {		
		return "error/403";
	}
	
	/**
	 * Méthode en GET pour /404 pour indiquer que la ressource demandée n'existe pas
	 * @return 404.html
	 */
	@GetMapping("/error")
	public String error() {
		return "error/404";
	}	
}

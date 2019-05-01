package ar.edu.unlam.scaw.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.scaw.app.commons.ScawConstants;

@Controller
public class LoginController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping(ScawConstants.GET_MAPPING_LOGIN)
	public String login(@RequestParam(value = "error" , required = false) String error,
			@RequestParam(value = "logout" , required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		if(!StringUtils.isEmpty(principal)) {
			flash.addAttribute("info", "Ya ha iniciado sesión anteriormente");
			return ScawConstants.REDIRECT;
		}
		if(StringUtils.isEmpty(error)) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		if(!StringUtils.isEmpty(logout)) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		return ScawConstants.VIEW_LOGIN;
	}
}

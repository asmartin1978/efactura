package es.application.ms_springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.application.ms_springmvc.service.FacturaService;

@Controller
public class MainController  {

	@Autowired
	FacturaService factura;
	
	@GetMapping("/home")
    public String home(Model model) {       
		return root(model);
    }
	
	@GetMapping("/")
    public String root(Model model) {
		model.addAttribute("facturas", factura.getAllFacturas());
        return "home";
    }
	
	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
	
	
	@GetMapping("/usuario")
    public String usuario() {
        return "/usuario";
    }
		
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
	
	
}
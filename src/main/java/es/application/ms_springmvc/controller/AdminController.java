package es.application.ms_springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.application.ms_springmvc.controller.validators.UserValidator;
import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.service.UsuarioService;

@Controller
public class AdminController {

	
	@Autowired
	UserValidator uservalidator;
	
	@Autowired
	UsuarioService usuarioservice;
		
	@GetMapping("/administrador/main")
    public String admin(Model model) {
		model.addAttribute("usuarios" , usuarioservice.findAllUsers());
		model.addAttribute("userForm" , new UsuarioDTO());
        return "/admin";
    }
	
	@GetMapping("/administrador/usuario")
    public String nuevousuario(Model model) {
		
		UsuarioDTO us = new UsuarioDTO();
		model.addAttribute("userForm" , us);
        return "fragments/nuevousuarioform";
    }
	
	@GetMapping("/administrador/usuario/{username}")
    public String getusuario(@PathVariable("username") String username ,Model model) {
		
		UsuarioDTO us = usuarioservice.findByUsername(username);
		model.addAttribute("userForm" , us);
        return "admin :: editarusuarioform";
    }
	
	
	@PostMapping("/administrador/usuario/{username}")
    public String updateusuario(@ModelAttribute("userForm") UsuarioDTO userForm, BindingResult bindingResult,Model model) {
		
		//Validacion
		uservalidator.validate(userForm, bindingResult);
		
		//Si hay errores -> Se informa
		if(bindingResult.hasErrors()){
			return "/admin";
		}
		//Si no hay errores -> Se guarda el usuario y se recarga la lista
		usuarioservice.save(userForm);
		model.addAttribute("usuarios" , usuarioservice.findAllUsers());
		return "/admin";
    }
	
	
	@PostMapping("/administrador/usuario")
	public String altaUsuario(@ModelAttribute("userForm") UsuarioDTO userForm, BindingResult bindingResult , Model model){
		
		//Validacion
		uservalidator.validate(userForm, bindingResult);	
		//Si hay errores -> Se informa
		if(bindingResult.hasErrors()){		
			return "/fragments/nuevousuarioform";
		}
		//Si no hay errores -> Se guarda el usuario y se recarga la lista
		usuarioservice.save(userForm);
		model.addAttribute("usuarios" , usuarioservice.findAllUsers());
		model.addAttribute("mensaje" , "El usuario ha sido creado con exito");
		return "/fragments/resultinmodal";
	}
	
}

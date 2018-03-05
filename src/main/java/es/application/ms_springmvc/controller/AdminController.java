package es.application.ms_springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import es.application.ms_springmvc.controller.validators.NewUserValidator;
import es.application.ms_springmvc.controller.validators.UserValidator;
import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.service.UsuarioService;

@Controller
public class AdminController {

	
	@Autowired
	@Qualifier("UserValidator")
	UserValidator uservalidator;
	
	@Autowired
	@Qualifier("NewUserValidator")
	NewUserValidator newuservalidator;
	
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
		//Se incluye una variable en el modelo para que la vista muestre el formulario de ALTA de nuevo usuario
		model.addAttribute("edicion", false);
        return "fragments/nuevousuarioform";
    }
	
	@GetMapping("/administrador/usuario/{username}")
    public String getusuario(@PathVariable("username") String username ,Model model) {
		
		UsuarioDTO us = usuarioservice.findByUsername(username);
		model.addAttribute("userForm" , us);
		//Se incluye una variable en el modelo para que la vista muestre el formulario de EDICION de nuevo usuario
		model.addAttribute("edicion", true);
        return "fragments/nuevousuarioform";
    }
	
	
	@PostMapping("/administrador/usuario/{username}")
    public String updateusuario(@ModelAttribute("userForm") UsuarioDTO userForm, BindingResult bindingResult,Model model) {
		
		//Validacion
		uservalidator.validate(userForm, bindingResult);
		
		//Si hay errores -> Se informa
		if(bindingResult.hasErrors()){
			model.addAttribute("edicion", true);
			return "fragments/nuevousuarioform";
		}
		//Si no hay errores -> Se guarda el usuario y se recarga la lista
		usuarioservice.update(userForm);
		model.addAttribute("mensaje" , "El usuario ha sido editado correctamente");
		return "/fragments/resultinmodal";
    }
	
	
	@PostMapping("/administrador/usuario")
	public String altaUsuario(@ModelAttribute("userForm") UsuarioDTO userForm, BindingResult bindingResult , Model model){
		
		//Validacion
		newuservalidator.validate(userForm, bindingResult);	
		//Si hay errores -> Se informa
		if(bindingResult.hasErrors()){
			model.addAttribute("edicion", false);
			return "fragments/nuevousuarioform";
		}
		//Si no hay errores -> Se guarda el usuario y se recarga la lista
		usuarioservice.save(userForm);
		model.addAttribute("mensaje" , "El usuario ha sido creado con exito");
		return "/fragments/resultinmodal";
	}
	
}

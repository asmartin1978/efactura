package es.application.ms_springmvc.controller.validators;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.service.UsuarioService;

@Component
public class UserValidator implements Validator {
    
	@Autowired
    private UsuarioService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UsuarioDTO user = (UsuarioDTO) o;

        //Validacion de campos obligatorios
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        
        
        //Validacion de un email correcto
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(user.getMail()).matches())) {
        	errors.rejectValue("mail", "user.email.invalid");
         }
        
        
        //Validacion del tamaño del usuario
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
               
        
        //Validacion de que no exista ya el nombre del usuario
        if(user.getUsername()!=null && !"".equals(user.getUsername())){
	        if (userService.findByUsername(user.getUsername()) != null) {
	            errors.rejectValue("username", "Duplicate.userForm.username");
	        }
        }
  
        //Validacion del tamaño de la password
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}


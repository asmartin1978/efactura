package es.application.ms_springmvc.controller.validators;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.service.UsuarioService;

@Component
@Qualifier("UserValidator")
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
                
        //Validacion de un email correcto
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        
        if (!(pattern.matcher(user.getMail()).matches())) {
        	errors.rejectValue("mail", "user.email.invalid");
         }
        
        Pattern paternusername = Pattern.compile("[a-zA-Z0-9]{3,10}");
        
        if (!(paternusername.matcher(user.getUsername()).matches())) {
        	errors.rejectValue("username", "userForm.username.invalid");
         }

    }
}


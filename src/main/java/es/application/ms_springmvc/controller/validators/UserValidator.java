package es.application.ms_springmvc.controller.validators;


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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        if(user.getUsername()!=null && !"".equals(user.getUsername())){
	        if (userService.findByUsername(user.getUsername()) != null) {
	            errors.rejectValue("username", "Duplicate.userForm.username");
	        }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}


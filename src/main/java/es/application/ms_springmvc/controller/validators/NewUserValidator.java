package es.application.ms_springmvc.controller.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.service.UsuarioService;

@Component
@Qualifier("NewUserValidator")
public class NewUserValidator extends UserValidator {
    
	@Autowired
    private UsuarioService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UsuarioDTO user = (UsuarioDTO) o;

        super.validate(o, errors);
        
        //Validacion de que no exista ya el nombre del usuario
        if(user.getUsername()!=null && !"".equals(user.getUsername())){
	        if (userService.findByUsername(user.getUsername()) != null) {
	            errors.rejectValue("username", "Duplicate.userForm.username");
	        }
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        
      //Validacion del tamaño de la password
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}


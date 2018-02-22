package es.application.ms_springmvc.model.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private String username;
	private String nombre;
	private String apellidos;
	private String mail;
	private String password;
	private Set<RolDTO> roles;
	
		
}

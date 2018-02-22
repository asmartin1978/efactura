package es.application.ms_springmvc.service;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class MyUserDetails extends User {

	
	private String nombre;
	private String apellidos;
	
	public MyUserDetails(String username, String password, 
				Collection<? extends GrantedAuthority> authorities , String nombre, String apellidos) {
		
		super(username, password, authorities);
		this.nombre = nombre;
		this.apellidos = apellidos;
		
	}

}

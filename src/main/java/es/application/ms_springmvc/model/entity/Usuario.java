package es.application.ms_springmvc.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name="usuario")
@Getter
@Setter
public class Usuario {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@NotNull
	private String username;
	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	@NotNull
	private String mail;
	@NotNull
	private String password;
	
	
	@ManyToMany
	@JoinTable(name="user_x_roles",
			joinColumns = { @JoinColumn(name = "fk_usu") },
			inverseJoinColumns = { @JoinColumn(name = "fk_rol") })
	private Set<Rol> roles;
		
}

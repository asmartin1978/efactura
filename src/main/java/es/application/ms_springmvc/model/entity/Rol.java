package es.application.ms_springmvc.model.entity;

import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 @NotNull
	 private String codigo;
	 @NotNull
	 private String descripcion;
	 
	 @NotNull
	 @ManyToMany(mappedBy="roles")
	 private Set<Usuario> usuarios;
}

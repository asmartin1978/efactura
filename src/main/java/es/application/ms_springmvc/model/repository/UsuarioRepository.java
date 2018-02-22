package es.application.ms_springmvc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.application.ms_springmvc.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}

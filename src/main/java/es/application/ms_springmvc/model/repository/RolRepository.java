package es.application.ms_springmvc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.application.ms_springmvc.model.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

}

package es.application.ms_springmvc.service;

import java.util.List;

import es.application.ms_springmvc.model.dto.UsuarioDTO;


public interface UsuarioService {
    
	void save(UsuarioDTO user);
    UsuarioDTO findByUsername(String username);
    public List<UsuarioDTO> findAllUsers();
}

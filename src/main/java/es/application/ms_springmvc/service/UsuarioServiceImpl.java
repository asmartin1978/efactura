package es.application.ms_springmvc.service;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import es.application.ms_springmvc.model.dto.UsuarioDTO;
import es.application.ms_springmvc.model.entity.Usuario;
import es.application.ms_springmvc.model.repository.RolRepository;
import es.application.ms_springmvc.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
	@Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RolRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public void save(UsuarioDTO user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(DtoToEntity(user));
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
    	if(userRepository.findByUsername(username)!=null){
    		return convertToDTO(userRepository.findByUsername(username));
    	}else {return null;}
    }
    
    public List<UsuarioDTO> findAllUsers(){
    	return userRepository.findAll().stream().map(usu -> convertToDTO(usu)).collect(Collectors.toList());
    }
    
    private UsuarioDTO convertToDTO(Usuario usu){
    	return modelMapper.map(usu, UsuarioDTO.class);
    }
    
    private Usuario DtoToEntity(UsuarioDTO usu){
    	return modelMapper.map(usu, Usuario.class);
    }
    
	@Override
	public void update(UsuarioDTO user) {
		//solo actualizo si existe, y ademas no deja tocar la password con este metodo
		Usuario theEntity = userRepository.findByUsername(user.getUsername());
		//TODO: Hacer esto con modelmapper
		theEntity.setMail(user.getMail());
		theEntity.setApellidos(user.getApellidos());
		theEntity.setNombre(user.getNombre());	
		userRepository.save(theEntity);
	}
       
}
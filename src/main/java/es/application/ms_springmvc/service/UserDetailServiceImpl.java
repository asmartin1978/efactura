package es.application.ms_springmvc.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.application.ms_springmvc.model.entity.Rol;
import es.application.ms_springmvc.model.entity.Usuario;
import es.application.ms_springmvc.model.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Usuario user = usuarioRepo.findByUsername(username);	
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Rol role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCodigo()));
        }
        return new MyUserDetails(user.getUsername(), user.getPassword(), grantedAuthorities, user.getNombre() , user.getApellidos());		
	}
	
}

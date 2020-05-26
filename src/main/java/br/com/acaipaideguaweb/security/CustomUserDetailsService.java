package br.com.acaipaideguaweb.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.Usuario;
import br.com.acaipaideguaweb.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService userService;

	@Autowired
	public CustomUserDetailsService(UsuarioService userRepository) {
		this.userService = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userService.findByEmail(username);
		if (null == user) {
			throw new UsernameNotFoundException("Não existe o usuario: " + username);
		} else {
			List<String> userRoles = userService.findByRoles(user);
			return new CustomUserDetails(user, userRoles);
		}
	}
}
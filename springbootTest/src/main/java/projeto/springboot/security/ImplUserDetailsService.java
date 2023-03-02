package projeto.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import projeto.springboot.model.Usuario;
import projeto.springboot.repository.UsuarioRepository;

@Service
@Transactional
public class ImplUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		  if(usuario==null) {
			  throw new UsernameNotFoundException("usuario nao encontrado");
		  }
	        return new User(usuario.getLogin(), usuario.getPassword(), true, true, true,true, usuario.getAuthorities());
	    }

}

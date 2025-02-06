package br.gov.rj.faetec.remocao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		
		UsuarioEntity usuario = this.usuarioRepository.getOneByEmail(email);		
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}	
		return new User(usuario.getEmail(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
		
	}

}
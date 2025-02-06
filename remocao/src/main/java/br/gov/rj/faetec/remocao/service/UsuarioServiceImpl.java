package br.gov.rj.faetec.remocao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public void save(UsuarioEntity usuarioEntity) throws Exception {
		
		usuarioRepository.save(usuarioEntity);
		
	}

}

package br.gov.rj.faetec.remocao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.rj.faetec.remocao.entity.CandidaturaEntity;
import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.CandidaturaRepository;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;

@Service
public class CandidaturaServiceImpl  implements CandidaturaService{
	
    @Autowired
	CandidaturaRepository candidaturaRepository;
    
    @Autowired
   	UsuarioRepository usuarioRepository;

	@Override
	public List<CandidaturaEntity> findAllByEmail(String email) {
		
		   UsuarioEntity usuarioEntity = new UsuarioEntity();
		   usuarioEntity = this.usuarioRepository.getOneByEmail(email);
		
		return candidaturaRepository.findAllByIdUsuario(usuarioEntity.getIdUsuario());
	}

}

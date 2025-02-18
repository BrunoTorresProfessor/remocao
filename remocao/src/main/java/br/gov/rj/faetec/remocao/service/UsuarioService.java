package br.gov.rj.faetec.remocao.service;

import java.util.List;

import br.gov.rj.faetec.remocao.entity.UnidadeEntity;
import br.gov.rj.faetec.remocao.entity.UsuarioEntity;

public interface UsuarioService {
	
	void save(UsuarioEntity usuarioEntity) throws Exception;
	String recuperarSenha(UsuarioEntity usuarioEntity) throws Exception;
	String realizarCandidatura(UnidadeEntity unidadeEntity,String email) throws Exception;
	List<UsuarioEntity> listarPermutasUsuario(String email);

}

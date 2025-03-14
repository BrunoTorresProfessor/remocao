package br.gov.rj.faetec.remocao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	  UsuarioEntity getOneByEmail(String email);
	  
	  @Modifying
	  @Query(value ="update remocao.usuario "
	  		+ "set senha = ?1 "
	  		+ "where email=?2 ", nativeQuery = true)
	  @Transactional
	  void alterarSenha(String novaSenha, String email);
	  
	  @Query(value ="select candidatura.*\r\n"
	  		+ "from (\r\n"
	  		+ "SELECT usu.nome, \r\n"
	  		+ "usu.email, \r\n"
	  		+ "u.nome as nome_unidade, \r\n"
	  		+ "car.nome as nome_cargo, \r\n"
	  		+ "ac.nome as nome_area_conhecimento, \r\n"
	  		+ "ch.carga_horaria,\r\n"
	  		+ "v.nome as nome_vinculo,\r\n"
	  		+ "c.id_unidade,\r\n"
	  		+ "usu.id_cargo,\r\n"
	  		+ "usu.id_area_conhecimento,\r\n"
	  		+ "usu.id_carga_horaria,\r\n"
	  		+ "usu.id_vinculo, usu.id_usuario, usu.senha\r\n"
	  		+ "FROM remocao.candidatura c, \r\n"
	  		+ "remocao.usuario usu, \r\n"
	  		+ "remocao.unidade u, \r\n"
	  		+ "remocao.cargo car,\r\n"
	  		+ "remocao.area_conhecimento ac,\r\n"
	  		+ "remocao.carga_horaria ch,\r\n"
	  		+ "remocao.vinculo v\r\n"
	  		+ "where c.id_unidade = u.id_unidade\r\n"
	  		+ "and c.id_unidade = u.id_unidade\r\n"
	  		+ "and usu.id_usuario = c.id_usuario\r\n"
	  		+ "and usu.id_cargo = car.id_cargo\r\n"
	  		+ "and usu.id_area_conhecimento = ac.id_area_conhecimento\r\n"
	  		+ "and usu.id_carga_horaria = ch.id_carga_horaria\r\n"
	  		+ "and usu.id_vinculo = v.id_vinculo\r\n"
	  		+ "and c.id_usuario <> ?1) as candidatura,\r\n"
	  		+ "remocao.usuario usuario\r\n"
	  		+ "where usuario.id_unidade = candidatura.id_unidade\r\n"
	  		+ "and usuario.id_cargo = candidatura.id_cargo\r\n"
	  		+ "and usuario.id_area_conhecimento = candidatura.id_area_conhecimento\r\n"
	  		+ "and usuario.id_carga_horaria = candidatura.id_carga_horaria\r\n"
	  		+ "and usuario.id_vinculo = candidatura.id_vinculo\r\n"
	  		+ "and usuario.id_usuario = ?1", nativeQuery = true)
	  List<UsuarioEntity> listarPermutasUsuario(Long idUsuario);
}

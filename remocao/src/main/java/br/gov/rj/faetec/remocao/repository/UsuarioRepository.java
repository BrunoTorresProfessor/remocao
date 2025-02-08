package br.gov.rj.faetec.remocao.repository;

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
}

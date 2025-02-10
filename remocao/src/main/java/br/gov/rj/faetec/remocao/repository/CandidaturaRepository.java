package br.gov.rj.faetec.remocao.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.remocao.entity.CandidaturaEntity;
import jakarta.transaction.Transactional;


@Repository
public interface CandidaturaRepository extends JpaRepository<CandidaturaEntity, Long>{
	
	 @Modifying
	 @Query(value ="insert into remocao.candidatura (id_usuario,id_unidade,data_cadastro) "
	  		+ "values(:id_usuario,:id_unidade,:data_cadastro) ", nativeQuery = true)
	 @Transactional
	 void realizarCandidatura(@Param("id_usuario") Long id_unidade,@Param("id_unidade") Long siape, @Param("data_cadastro") Timestamp data_cadastro);
	 
	 
	 List<CandidaturaEntity> findAllByIdUsuario(Long idUsuario);
	   
	

}

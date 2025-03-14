package br.gov.rj.faetec.remocao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidatura" , schema = "remocao")
public class CandidaturaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidatura")
	private Long idCandidatura;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "id_unidade", insertable=false, updatable=false)
	private Long idUnidade;	
	
	@Column(name = "data_cadastro")
	private Timestamp dataCadastro;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_unidade", referencedColumnName = "id_unidade")
    private UnidadeEntity unidadeEntity;

	public UnidadeEntity getUnidadeEntity() {
		return unidadeEntity;
	}

	public void setUnidadeEntity(UnidadeEntity unidadeEntity) {
		this.unidadeEntity = unidadeEntity;
	}

	public Long getIdCandidatura() {
		return idCandidatura;
	}

	public void setIdCandidatura(Long idCandidatura) {
		this.idCandidatura = idCandidatura;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}
	public String getDataFormatada() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dataFormatada = dataCadastro.toLocalDateTime().format(formatter);
		
		return dataFormatada;
	}


	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}	
	
	
	  
	

	
	

}

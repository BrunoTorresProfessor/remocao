package br.gov.rj.faetec.remocao.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "area_conhecimento" , schema = "remocao")
public class AreaConhecimentoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_area_conhecimento")
	private Long idAreaConhecimento;
	
	@Column(name = "nome")
	private String nome;

	public Long getIdAreaConhecimento() {
		return idAreaConhecimento;
	}

	public void setIdAreaConhecimento(Long idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    

	
	

}

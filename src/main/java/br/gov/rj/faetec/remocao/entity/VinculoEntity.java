package br.gov.rj.faetec.remocao.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vinculo", schema = "remocao")
public class VinculoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vinculo")
	private Long idVinculo;
	
	@Column(name = "nome")
	private String nome;

	public Long getIdVinculo() {
		return idVinculo;
	}

	public void setIdVinculo(Long idVinculo) {
		this.idVinculo = idVinculo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}

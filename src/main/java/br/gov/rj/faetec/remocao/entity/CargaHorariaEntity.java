package br.gov.rj.faetec.remocao.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carga_horaria" , schema = "remocao")
public class CargaHorariaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carga_horaria")
	private Long idCargaHoraria;
	
	@Column(name = "carga_horaria")
	private String cargaHoraria;

	public Long getIdCargaHoraria() {
		return idCargaHoraria;
	}

	public void setIdCargaHoraria(Long idCargaHoraria) {
		this.idCargaHoraria = idCargaHoraria;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	


}

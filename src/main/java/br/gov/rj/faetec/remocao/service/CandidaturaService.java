package br.gov.rj.faetec.remocao.service;

import java.util.List;

import br.gov.rj.faetec.remocao.entity.CandidaturaEntity;

public interface CandidaturaService {
	
	 List<CandidaturaEntity> findAllByEmail(String email);

}

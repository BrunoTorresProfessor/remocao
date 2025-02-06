package br.gov.rj.faetec.remocao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.remocao.entity.CargoEntity;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Long>{

}

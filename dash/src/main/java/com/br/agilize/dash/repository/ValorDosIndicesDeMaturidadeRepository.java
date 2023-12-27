package com.br.agilize.dash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.ValorDosIndicesDeMaturidadeEntity;

@Repository
public interface ValorDosIndicesDeMaturidadeRepository extends JpaRepository<ValorDosIndicesDeMaturidadeEntity, Long> {

}
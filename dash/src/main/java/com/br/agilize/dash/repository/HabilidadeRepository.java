package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.HabilitadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadeRepository extends JpaRepository<HabilitadeEntity, Long> {
}

package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.AcoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcoesRepository extends JpaRepository<AcoesEntity, Long> {
}

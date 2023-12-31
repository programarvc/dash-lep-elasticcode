package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;

@Repository
public interface EsteiraDeDesenvolvimentoRepository extends JpaRepository<EsteiraDeDesenvolvimentoEntity, Long> {
    
}
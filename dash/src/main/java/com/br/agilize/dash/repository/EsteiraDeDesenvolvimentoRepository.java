package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.EsteiraDeDesenvolvimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsteiraDeDesenvolvimentoRepository extends JpaRepository<EsteiraDeDesenvolvimentoEntity, Long> {
    
}

package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.JornadaDeTransformacaoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaDeTransformacaoRepository extends JpaRepository<JornadaDeTransformacaoEntity, Long> {
    
}


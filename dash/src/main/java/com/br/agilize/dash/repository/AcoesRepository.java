package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.AcoesEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcoesRepository extends JpaRepository<AcoesEntity, Long> {

    Optional<AcoesEntity> findByNome(String nome);
    
}
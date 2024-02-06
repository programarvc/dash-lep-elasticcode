package com.br.agilize.dash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;


@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
    Optional<ColaboradorEntity> findByNome(String nome);

@Query("SELECT tc.colaborador FROM TimeColaboradorEntity tc WHERE tc.time.esteira.id = :esteiraId")
List<ColaboradorEntity> findColaboradoresByEsteiraId(@Param("esteiraId") Long esteiraId);

}
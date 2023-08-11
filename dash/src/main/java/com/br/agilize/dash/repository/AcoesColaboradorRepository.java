package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.AcoesColaboradorEntity;
import com.br.agilize.dash.model.entity.AcoesEntity;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcoesColaboradorRepository extends JpaRepository<AcoesColaboradorEntity, Long> {

    List<AcoesColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);
}

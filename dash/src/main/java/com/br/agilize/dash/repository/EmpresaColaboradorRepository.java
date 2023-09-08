package com.br.agilize.dash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.EmpresaColaboradorEntity;

@Repository
public interface EmpresaColaboradorRepository extends JpaRepository<EmpresaColaboradorEntity, Long> {

    List<EmpresaColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);
}

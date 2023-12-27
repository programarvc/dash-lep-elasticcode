package com.br.agilize.dash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.EmpresaColaboradorEntity;
import com.br.agilize.dash.model.entity.EmpresaEntity;

@Repository
public interface EmpresaColaboradorRepository extends JpaRepository<EmpresaColaboradorEntity, Long> {

    List<EmpresaColaboradorEntity> findByColaborador(ColaboradorEntity colaborador);

    List<EmpresaColaboradorEntity> findByEmpresa(EmpresaEntity empresa);

    List<EmpresaColaboradorEntity> findByEmpresaId(Long id);

    EmpresaColaboradorEntity findByColaboradorIdAndEmpresaId(Long colaboradorId, Long empresaId);



}
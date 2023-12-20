package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.EmpresaEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    @Query(value = "SELECT new EmpresaEntity(id, nome) FROM EmpresaEntity")
    Page<EmpresaEntity> findAllEmp(Pageable pageable);
}
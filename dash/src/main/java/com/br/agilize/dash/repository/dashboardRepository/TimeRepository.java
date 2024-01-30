package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;


@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {

    @Query(value = "SELECT t FROM TimeEntity t")
    Page<TimeEntity> findAllTeam(Pageable pageable);

    boolean existsByNomeTime(String nomeTime);
    
}
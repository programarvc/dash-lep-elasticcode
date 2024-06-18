package com.br.agilize.dash.repository.dashboardRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;


@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {

    @Query(value = "SELECT t FROM TimeEntity t")
    Page<TimeEntity> findAllTeam(Pageable pageable);

    boolean existsByNomeTime(String nomeTime);

    @Query(value = "SELECT * FROM TimeEntity WHERE esteira_id = :esteiraId", nativeQuery = true)
    List<TimeEntity> findTimesByEsteiraId(@Param("esteiraId") Long esteiraId);
    
}
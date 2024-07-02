package com.br.agilize.dash.repository.dashboardRepository;

import com.br.agilize.dash.model.entity.dashboardEntity.PromptsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromptsHistoryRepository extends JpaRepository<PromptsHistoryEntity, Long> {

    //Retorna contagem de prompts por userEsteiraId
    @Query("SELECT COUNT(p) FROM PromptsHistoryEntity p WHERE p.userEsteira.id = :userEsteiraId")
    Long countByUserEsteiraId(@Param("userEsteiraId") Long userEsteiraId);

    @Query("SELECT p FROM PromptsHistoryEntity p WHERE p.userEsteira.id = :userEsteiraId")
    List<PromptsHistoryEntity> findByUserEsteiraId(@Param("userEsteiraId") Long userEsteiraId);
}

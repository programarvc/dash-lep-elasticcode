package com.br.agilize.dash.repository.dashboardRepository;

import com.br.agilize.dash.model.entity.dashboardEntity.PromptsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptsHistoryRepository extends JpaRepository<PromptsHistoryEntity, Long> {

}

package com.br.agilize.dash.repository.dashboardRepository;

import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrFromGitHubRepository extends JpaRepository<PrFromGitHubEntity, Long> {
}

package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;

@Repository
public interface VcsPullRequestRepository extends JpaRepository<VcsPullRequestEntity, Long> {
    Optional<VcsPullRequestEntity> findByAuthorAndTitleAndMergedAt(String author, String title, String mergedAt);
}
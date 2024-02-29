package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;

@Repository
public interface VcsPullRequestRepository extends JpaRepository<VcsPullRequestEntity, Long> {
    
}
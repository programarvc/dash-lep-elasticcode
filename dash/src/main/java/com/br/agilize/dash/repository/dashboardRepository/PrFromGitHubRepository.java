package com.br.agilize.dash.repository.dashboardRepository;

import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrFromGitHubRepository extends JpaRepository<PrFromGitHubEntity, Long> {
  
    PrFromGitHubEntity findByPrAuthorAndCreatedAt(String prAuthor, String createdAt);
    
    // Query para contar a quantidade de PRs de um colaborador por id
    @Query(value = "SELECT new map(c.nome as nome, COUNT(p.id) as countPr) " +
        "FROM PrFromGitHubEntity p " +
        "JOIN p.colaborador c " +
        "WHERE p.mergedAt IS NOT NULL " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.nome")
    Map<String, Object> countByColaboradorId(@Param("colaboradorId") Long colaboradorId);

}


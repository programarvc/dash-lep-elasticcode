package com.br.agilize.dash.repository.dashboardRepository;

import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrFromGitHubRepository extends JpaRepository<PrFromGitHubEntity, Long> {
  
    PrFromGitHubEntity findByPrAuthorAndCreatedAtAndMergedAt(String prAuthor, String createdAt, String mergedAt);
    
    // Query para buscar a quantidade total de PRs de um colaborador por id
    @Query(value = "SELECT new map(c.nome as nome, COUNT(p.id) as countPr) " +
        "FROM PrFromGitHubEntity p " +
        "JOIN p.colaborador c " +
        "WHERE p.mergedAt IS NOT NULL " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.nome")
    Map<String, Object> countByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 90 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM githubprsentity p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.data_de_merge_da_pr IS NOT NULL " +
        "AND TO_DATE(p.data_de_merge_da_pr, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '90 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast90DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 30 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM githubprsentity p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.data_de_merge_da_pr IS NOT NULL " +
        "AND TO_DATE(p.data_de_merge_da_pr, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '30 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast30DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 7 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM githubprsentity p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.data_de_merge_da_pr IS NOT NULL " +
        "AND TO_DATE(p.data_de_merge_da_pr, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '7 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast7DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);


}


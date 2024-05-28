package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;

@Repository
public interface VcsPullRequestRepository extends JpaRepository<VcsPullRequestEntity, Long> {
    Optional<VcsPullRequestEntity> findByAuthorAndTitleAndMergedAt(String author, String title, String mergedAt);

    // Query para buscar a quantidade total de PRs de um colaborador por id dentro de um ano
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '1 year' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast1YearByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade total de PRs de um colaborador por id esse ano
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= DATE_TRUNC('year', CURRENT_DATE) " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsThisYearByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade total de PRs de um colaborador por id ano passado
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= DATE_TRUNC('year', CURRENT_DATE) - INTERVAL '1 year' " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') < DATE_TRUNC('year', CURRENT_DATE) " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLastYearByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 90 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '90 days' " +
         "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast90DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 30 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast60DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 30 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '30 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast30DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);


    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 7 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '7 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsLast7DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    //Querry para buscar a quantidade total de PRs de um colaborador por id
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countAllPrsByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de PRs de um colaborador por id em um intervalo de datas
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "AND TO_DATE(p.merged_at, 'YYYY-MM-DD') BETWEEN :startDate AND :endDate " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countPrsInDateRangeByColaboradorId(@Param("colaboradorId") Long colaboradorId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Query para buscar a quantidade total de PRs nos últimos 30, 60 e 90 dias
    @Query(value = "SELECT " +
        "COUNT(p.id) FILTER (WHERE TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '30 days') as countPr30Days, " +
        "COUNT(p.id) FILTER (WHERE TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days') as countPr60Days, " +
        "COUNT(p.id) FILTER (WHERE TO_DATE(p.merged_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '90 days') as countPr90Days " +
        "FROM vcs_pull_request p " +
        "WHERE p.merged_at IS NOT NULL", nativeQuery = true)
    Map<String, Object> countPrsLast30And60And90Days();

    // Query para buscar os top 5 colaboradores com mais PRs realizadas e a quantidade total de PRs
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(p.id) as countPr, (SELECT COUNT(*) FROM vcs_pull_request) as totalPrs " +
        "FROM vcs_pull_request p " +
        "JOIN colaboradorentity c ON p.colaborador_id = c.id " +
        "WHERE p.merged_at IS NOT NULL " +
        "GROUP BY c.id, c.nome " +
        "ORDER BY countPr DESC " +
        "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> findTop5ColaboradoresAndTotalPrs();

    @Query(value = "SELECT COUNT(p.id) as totalPrs FROM vcs_pull_request p WHERE p.merged_at IS NOT NULL", nativeQuery = true)
    List<Map<String, Object>> getTotalPrs();
}
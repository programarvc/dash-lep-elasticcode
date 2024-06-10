package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.br.agilize.dash.model.entity.dashboardEntity.JiraActivitiesEntity;

@Repository
public interface JiraActivitiesRepository extends JpaRepository<JiraActivitiesEntity, Long> {
    
    JiraActivitiesEntity findByStatusDetail( String statusDetail);

    Optional<JiraActivitiesEntity> findByNameAndSprintAndPriorityAndUpdatedAtAndTypeDetailAndSource(String name, String sprint, String priority, String updated_at, String type_detail, String source);

    //Query para buscar a quantidade de histórias por epic especifico.
    @Query(value = "SELECT COUNT(j.id) AS story_count " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'História' " +
        "AND j.epic = 'Jira|SCRUM-86'", nativeQuery = true)
    Map<String, Object> countStories();

    // Query para buscar a quantidade total de tarefas
    @Query(value = "SELECT COUNT(j.id) AS story_count " +
        "FROM tms_task j " +
        "WHERE j.type_detail IN ('História', 'Subtarefa', 'Bug')" +
        "AND source = 'Jira'", nativeQuery = true)
    Map<String, Object> countAllStories();

    // Query para buscar a quantidade total de tarefas nos últimos 60 dias
    @Query(value = "SELECT COUNT(j.id) AS story_count_last60days " +
        "FROM tms_task j " +
        "WHERE j.type_detail IN ('História', 'Subtarefa', 'Bug') " +
        "AND source = 'Jira'" +
        "AND TO_DATE(updated_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days'", nativeQuery = true)
    Map<String, Object> countAllStoriesLast60Days();

    // Query para buscar a quantidade total de epics mais o nome dos epicos
    @Query(value = "SELECT j.name AS name, j.epic AS epic, COUNT(j.id) AS count_epics " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'Epic' " +
        "GROUP BY j.name, j.epic", nativeQuery = true)
    List<Map<String, Object>> countAndDetailsByTypeDetail();

    // Query para buscar a quantidade total de epics
    @Query(value = "SELECT COUNT(j.id) AS count_epics " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'Epic' ", nativeQuery = true)
    Map<String, Object> countAllEpics();

    // Query para buscar a quantidade de epics concluídos nos últimos 60 dias
    @Query(value = "SELECT COUNT(j.id) AS count_epics " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'Epic' " +
        "AND TO_DATE(j.updated_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days'", nativeQuery = true)
    Map<String, Object> countEpics();

    // Query para calcular a soma total de pontos e a média de pontos das tarefas concluídas nos epicos da esteira
    @Query(value = "SELECT COUNT(*) AS total_stories, SUM(CAST(points AS INTEGER)) AS total_points, ROUND(CAST((SUM(CAST(points AS INTEGER)) * 1.0 / COUNT(*)) AS NUMERIC), 2) AS average_points " +
        "FROM tms_task " +
        "WHERE type_detail IN ('História', 'Subtarefa', 'Bug') " +
        "AND source = 'Jira' " +
        "AND (status_detail = 'Concluido(Dev)' OR status_detail = 'DEPLOYED') " +
        "AND points IS NOT NULL", nativeQuery = true)
    Map<String, Object> calculateTotalAndAveragePoints();

    // Query para calcular a quantidade total de pontos das histórias totais
    @Query(value = "SELECT SUM(CAST(points AS INT)) AS total_points " +
        "FROM tms_task " +
        "WHERE type_detail  IN ('História', 'Subtarefa', 'Bug') " +
        "AND source = 'Jira'", nativeQuery = true)
    Map<String, Object> sumTotalPointsForJiraStories();

    // Query para calcular a quantidade total de pontos das histórias totais nos últimos 60 dias
    @Query(value = "SELECT SUM(CAST(points AS INT)) AS total_points_last60days " +
        "FROM tms_task " +
        "WHERE type_detail IN ('História', 'Subtarefa', 'Bug') " +
        "AND source = 'Jira' " +
        "AND TO_DATE(updated_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days'", nativeQuery = true)
    Map<String, Object> sumTotalPointsForJiraStoriesLast60Days();

}
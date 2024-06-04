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

    Optional<JiraActivitiesEntity> findByNameAndSprintAndPriority(String name, String sprint, String priority);

    //Query para buscar a quantidade de histórias por epic especifico.
    @Query(value = "SELECT COUNT(j.id) AS story_count " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'História' " +
        "AND j.epic = 'Jira|SCRUM-86'", nativeQuery = true)
    Map<String, Object> countStories();

    // Query para buscar a quantidade total de histórias
    @Query(value = "SELECT COUNT(j.id) AS story_count " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'História'", nativeQuery = true)
    Map<String, Object> countAllStories();

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
    @Query(value = "SELECT j.name AS name, j.epic AS epic, COUNT(j.id) AS count_epics " +
        "FROM tms_task j " +
        "WHERE j.type_detail = 'Epic' " +
        "AND (j.status_detail = 'DEPLOYED' OR j.status_detail = 'Concluido(Dev)') " +
        "AND TO_DATE(j.updated_at, 'YYYY-MM-DD') >= CURRENT_DATE - INTERVAL '60 days' " +
        "GROUP BY j.name, j.epic", nativeQuery = true)
    List<Map<String, Object>> countAndDetailsByTypeDetailAndStatusDetailAndUpdatedAt();

}
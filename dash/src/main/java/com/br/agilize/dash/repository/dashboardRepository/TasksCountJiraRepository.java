package com.br.agilize.dash.repository.dashboardRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import com.br.agilize.dash.model.entity.dashboardEntity.TasksCountJiraEntity;

@Repository
public interface TasksCountJiraRepository extends JpaRepository<TasksCountJiraEntity, Long> {
    
TasksCountJiraEntity findByAuthorAndStatusDetailAndMergedAt(String author, String statusDetail, LocalDateTime mergedAt);

    // Query para buscar a quantidade total de tasks concluidas de um colaborador por id
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countAllCompletedTasksByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade total de tasks concluidas de um colaborador por id dentro de um ano
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND EXTRACT(YEAR FROM t.merged_at) = EXTRACT(YEAR FROM current_date) " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksThisYearByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade total de tasks concluidas de um colaborador por id ano passado
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND EXTRACT(YEAR FROM t.merged_at) = EXTRACT(YEAR FROM current_date) - 1 " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksLastYearByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 7 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND t.merged_at >= current_date - interval '7 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksLast7DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 30 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND t.merged_at >= current_date - interval '30 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksLast30DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 60 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND t.merged_at >= current_date - interval '60 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksLast60DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    // Query para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 30 dias
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND t.merged_at >= current_date - interval '90 days' " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksLast90DaysByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    
    // Query para buscar a quantidade de tasks concluídas de um colaborador por id em um intervalo de datas
    @Query(value = "SELECT c.id as id, c.nome as nome, COUNT(t.id) as countTasks " +
        "FROM tasks_count_jira t " +
        "JOIN colaboradorentity c ON t.colaborador_id = c.id " +
        "WHERE t.status_detail = 'concluido' " +
        "AND DATE(t.merged_at) BETWEEN :startDate AND :endDate " +
        "AND c.id = :colaboradorId " +
        "GROUP BY c.id, c.nome", nativeQuery = true)
    Map<String, Object> countCompletedTasksInDateRangeByColaboradorId(@Param("colaboradorId") Long colaboradorId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
package com.br.agilize.dash.model.entity.dashboardEntity;

import java.time.LocalDateTime;

import com.br.agilize.dash.model.entity.ColaboradorEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tasks_count_jira")
@NoArgsConstructor
@AllArgsConstructor
public class TasksCountJiraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_name", columnDefinition = "TEXT")
    private String taskName;

    @Column(name = "status_detail", columnDefinition = "TEXT")
    private String statusDetail;

    @Column(name = "merged_at")
    private LocalDateTime mergedAt;

    @Column(name = "author", columnDefinition = "TEXT")
    private String author;

    @ManyToOne
    private ColaboradorEntity colaborador;

    @PrePersist
    public void prePersist() {
        mergedAt = LocalDateTime.now();
    }

}
package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class PromptsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stack;

    @Column(nullable = false)
    private String tipo_codigo;

    @Column(nullable = false)
    private String entidade;

    @Column
    private String tabela;

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @ManyToOne
    @JoinColumn(name = "user_esteira_id")
    private UserEsteiraEntity userEsteira;

    @ManyToOne
    @JoinColumn(name = "tms_task_id")
    private JiraActivitiesEntity jiraActivity;
}

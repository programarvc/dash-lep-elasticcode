package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Getters e Setters gerados pela anotação lombok @Data
@Data

@Entity
@Table(name = "pr_from_github")
public class PrFromGitHubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "pr_count")
    private Integer prCount;

    @ElementCollection
    @CollectionTable(name = "pr_dates", joinColumns = @JoinColumn(name = "pull_request_id"))
    @Column(name = "date")
    private List<String> prDates;

}

package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Getters e Setters gerados pela anotação lombok @Data
@Data

@Entity
@Table(name = "githubprsentity")
public class PrFromGitHubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Autor")
    private String prAuthor;

    @Column(name = "Data de Criação da PR")
    private String createdAt;

    @Column(name = "Data de Merge da PR")
    private String mergedAt;

    @Column(name = "repo_name")
    private String repoName;

}

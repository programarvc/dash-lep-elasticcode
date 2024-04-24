package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import com.br.agilize.dash.model.entity.ColaboradorEntity;

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

    @Column(name = "Data_de_Criação_da_PR")
    private String createdAt;

    @Column(name = "Data_de_Merge_da_PR")
    private String mergedAt;

    @Column(name = "repo_name")
    private String repoName;

    @ManyToOne
    private ColaboradorEntity colaborador;
}


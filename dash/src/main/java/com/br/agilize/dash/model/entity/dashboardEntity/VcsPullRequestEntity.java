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
@Table(name = "vcs_pull_request")
@NoArgsConstructor
@AllArgsConstructor
public class VcsPullRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "merged_at")
    private String mergedAt;
    @Column(name = "author", columnDefinition = "TEXT")
    private String author;


    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private ColaboradorEntity colaborador;

}
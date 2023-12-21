package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class CompetenciaColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ColaboradorEntity colaborador;

    @ManyToOne
    private CompetenciaEntity competencia;

    @Column
    private Integer progresso;

    @Column
    private Integer meta;
}
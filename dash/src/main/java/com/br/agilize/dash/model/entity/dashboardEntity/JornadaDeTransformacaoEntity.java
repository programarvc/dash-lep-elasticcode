package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class JornadaDeTransformacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer saude;

    @Column(nullable = false, name = "metricas_4")
    private Integer metricas4;

    @Column(nullable = false, name = "capacidade_dora")
    private Integer capacidadeDora;

    @ManyToOne
    @JoinColumn(name = "maturidade_id")
    private MaturidadeEntity maturidade;

    @Column(nullable = false, name = "media_de_jornada")    
    private Double mediaDeJornada;



}
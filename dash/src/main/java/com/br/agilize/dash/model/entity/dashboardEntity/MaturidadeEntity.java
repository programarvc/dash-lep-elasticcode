package com.br.agilize.dash.model.entity.dashboardEntity;

import java.time.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor
public class MaturidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private EsteiraDeDesenvolvimentoEntity esteira;

    @Column(nullable = false, name = "data_hora")
    private LocalDateTime dataHora;

    @Column
    private Integer numero;

    @Column(name = "lead_time")
    private Double leadTime;

    @Column(name = "lead_time_esperado")
    private Double leadTimeEsperado;

    @Column(name = "frequency_deployment")
    private Double frequencyDeployment;

    @Column(name = "frequency_deployment_esperado")
    private Double frequencyDeploymentEsperado;

    @Column(name = "change_failurerate")
    private Double changeFailureRate;

    @Column(name = "change_failurerate_esperado")
    private Double changeFailureRateEsperado;

    @Column(name = "time_torecovery")
    private Double timeToRecovery;

    @Column(name = "time_torecovery_esperado")
    private Double timeToRecoveryEsperado;
    
    @Column(name = "media_de_jornada")
    private Double mediaDeJornada;

    @Column
    private Double saude;

    @Column(name = "metricas_4")
    private Double metricas4;

    @Column(name = "capacidade_dora")
    private Double capacidadeDora;

    @PrePersist
    public void prePersist() {
       dataHora = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataHora = LocalDateTime.now();
        
    }

}
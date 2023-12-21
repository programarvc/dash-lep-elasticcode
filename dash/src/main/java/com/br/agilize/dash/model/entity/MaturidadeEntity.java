package com.br.agilize.dash.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class MaturidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @OneToOne
   private EsteiraDeDesenvolvimentoEntity esteira;

   @Column 
   private LocalDate data;

    @Column
    private Integer numero;

    @Column(name = "lead_time")
    private Integer leadTime;

    @Column(name = "frequency_deployment")
    private Integer frequencyDeployment;

    @Column(name = "change_failurerate")
    private Double changeFailureRate;

    @Column(name = "time_torecovery")
    private Integer timeToRecovery;

    @PrePersist
    public void prePersist() {
        data = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        data = LocalDate.now();
    }

}
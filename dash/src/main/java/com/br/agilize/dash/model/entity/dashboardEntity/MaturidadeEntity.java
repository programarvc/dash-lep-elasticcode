package com.br.agilize.dash.model.entity.dashboardEntity;

import java.time.LocalDate;

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

   @Column 
   private LocalDate data;

    @Column
    private Integer numero;

    @Column(name = "lead_time")
    private Double leadTime;

    @Column(name = "frequency_deployment")
    private Double frequencyDeployment;

    @Column(name = "change_failurerate")
    private Double changeFailureRate;

    @Column(name = "time_torecovery")
    private Double timeToRecovery;

    @PrePersist
    public void prePersist() {
        data = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        data = LocalDate.now();
    }

}
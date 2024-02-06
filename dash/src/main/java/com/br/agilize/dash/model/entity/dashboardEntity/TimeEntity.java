package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "nome_time")
    private String nomeTime;

    @ManyToOne
    @JoinColumn(name = "esteira_id")
    private EsteiraDeDesenvolvimentoEntity esteira;  
}
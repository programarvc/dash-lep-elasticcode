package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "pr_count")
public class  PrCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_colaborador_id")
    private TimeColaboradorEntity timeColaborador;

    private Integer count;

}
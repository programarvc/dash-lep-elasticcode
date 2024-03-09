package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor

public class IndiceDeSobrevivenciaDevEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_colaborador_id")
    private TimeColaboradorEntity timeColaborador;

    @Column(name = "valor_indice")
    private Double valorIndice;
}

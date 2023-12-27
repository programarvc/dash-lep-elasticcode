package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor

public class ValorDosIndicesDeMaturidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maturidade_id")
    private MaturidadeEntity maturidade;

    @ManyToOne
    @JoinColumn(name = "item_de_maturidade_id")
    private ItemDeMaturidadeEntity itemDeMaturidade;

    @Column(name = "valor_atingido")
    private Integer valorAtingido;

    @Column(name = "valor_esperado")
    private Integer valorEsperado;

}
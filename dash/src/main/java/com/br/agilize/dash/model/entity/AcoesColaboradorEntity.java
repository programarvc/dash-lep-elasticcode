package com.br.agilize.dash.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class AcoesColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ColaboradorEntity colaborador;

    @ManyToOne
    private AcoesEntity acao;

    @Column(columnDefinition = "integer default 0 not null " )
    private Integer progresso;
}

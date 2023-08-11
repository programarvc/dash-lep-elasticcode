package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
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

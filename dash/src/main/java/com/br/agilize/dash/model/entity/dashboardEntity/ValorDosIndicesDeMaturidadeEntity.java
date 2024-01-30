package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@NoArgsConstructor

public class ValorDosIndicesDeMaturidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_hora")
    private LocalDateTime dataHoraValor;

    @ManyToOne
    @JoinColumn(name = "maturidade_id")
    private MaturidadeEntity maturidade;

    @ManyToOne
    @JoinColumn(name = "item_de_maturidade_id")
    private ItemDeMaturidadeEntity itemDeMaturidade;

    @Column(name = "valor_atingido")
    private Double valorAtingido;

    @Column(name = "valor_esperado")
    private Double valorEsperado;

    @PrePersist
    public void prePersist() {
       dataHoraValor = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataHoraValor = LocalDateTime.now();
        
    }

}
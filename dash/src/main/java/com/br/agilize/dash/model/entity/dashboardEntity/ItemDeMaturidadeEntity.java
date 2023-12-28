package com.br.agilize.dash.model.entity.dashboardEntity;

import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class ItemDeMaturidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "tipo_maturidade")
    @Enumerated(EnumType.STRING)
    private TiposMaturidadeEnum tipoMaturidade;

    @Column(nullable = false)
    private String nome;


}
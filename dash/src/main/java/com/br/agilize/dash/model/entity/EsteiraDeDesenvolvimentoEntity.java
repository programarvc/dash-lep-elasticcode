package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class EsteiraDeDesenvolvimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    private EmpresaEntity empresa;

}
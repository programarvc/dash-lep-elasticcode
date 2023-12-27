package com.br.agilize.dash.model.entity;



import com.br.agilize.dash.model.enums.TiposEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor
public class EsteiraDeDesenvolvimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TiposEnum tipo;

    @ManyToOne
    private EmpresaEntity empresa;

}
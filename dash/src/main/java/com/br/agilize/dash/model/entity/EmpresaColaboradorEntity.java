package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class EmpresaColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private EmpresaEntity empresa;

    @ManyToOne
    private ColaboradorEntity colaborador;  

}

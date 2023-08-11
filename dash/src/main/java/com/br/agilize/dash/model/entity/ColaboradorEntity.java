package com.br.agilize.dash.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Entity
public class ColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String github;

    @ManyToMany
    @JoinTable(
            name = "colaborador_habilidade",
            joinColumns = @JoinColumn(name = "colaboradorId"),
            inverseJoinColumns = @JoinColumn(name = "habilidadeId")
    )
    private List<HabilitadeEntity> habilidades;

}
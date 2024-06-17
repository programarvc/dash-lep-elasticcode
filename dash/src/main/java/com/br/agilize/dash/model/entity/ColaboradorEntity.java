package com.br.agilize.dash.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

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

    @Column(columnDefinition = "TEXT")
    private String miniBio;

    @ManyToMany
    @JoinTable(name = "empresacolaboradorentity", joinColumns = @JoinColumn(name = "colaborador_id"), inverseJoinColumns = @JoinColumn(name = "empresa_id"))
    private List<EmpresaEntity> empresas;

    @ManyToMany
    @JoinTable(name = "habilidadecolaboradorentity", joinColumns = @JoinColumn(name = "colaborador_id"), inverseJoinColumns = @JoinColumn(name = "habilidade_id"))
    private List<HabilidadeEntity> habilidades;
}
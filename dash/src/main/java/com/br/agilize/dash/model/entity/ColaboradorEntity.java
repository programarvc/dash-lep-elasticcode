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

   @ManyToMany
   @JoinTable(name= "empresa_colaborador",
   joinColumns = @JoinColumn (name = "colaboradorId"),
   inverseJoinColumns = @JoinColumn(name ="empresaId"))
    private List<EmpresaEntity> empresa;

    @ManyToMany
    @JoinTable(
            name = "colaborador_habilidade",
            joinColumns = @JoinColumn(name = "colaboradorId"),
            inverseJoinColumns = @JoinColumn(name = "habilidadeId")
    )
    private List<HabilitadeEntity> habilidades;

}
package com.br.agilize.dash.model.entity.dashboardEntity;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String nome;

    @OneToOne(optional = true)
    @JoinColumn(name = "colaborador_id")
    private ColaboradorEntity colaborador;
}
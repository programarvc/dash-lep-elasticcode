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

    @OneToOne
    @JoinColumn(name = "colaborador_id", nullable = true)
    private ColaboradorEntity colaborador;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;
}
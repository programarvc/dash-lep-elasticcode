package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Entity
public class UserEsteiraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username_id" )
    private UserEntity username;

    @ManyToOne
    @JoinColumn(name = "esteira_id")
    private EsteiraDeDesenvolvimentoEntity esteira;

}
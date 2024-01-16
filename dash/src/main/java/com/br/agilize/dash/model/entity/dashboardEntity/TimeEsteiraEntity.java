package com.br.agilize.dash.model.entity.dashboardEntity;

import com.br.agilize.dash.model.entity.ColaboradorEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class TimeEsteiraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_id" )
    private TimeEntity time;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private ColaboradorEntity colaborador;  

    @ManyToOne
    @JoinColumn(name = "esteira_id")
    private EsteiraDeDesenvolvimentoEntity esteira;  

}
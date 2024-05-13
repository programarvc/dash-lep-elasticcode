package com.br.agilize.dash.model.entity.dashboardEntity;


import com.br.agilize.dash.model.entity.ColaboradorEntity;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class MetasColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meta_id" )
    private MetasOneAOneEntity meta;

    /*
     @ManyToOne
    @JoinColumn(name = "meta_id" )
    private List<MetasOneAOneEntity> metasNotas;
     */

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private ColaboradorEntity colaborador;  

    @Column( nullable = false, name = "data")
    private LocalDateTime data;

    @PrePersist
    public void prePersist() {
       data = LocalDateTime.now();
    } 

}
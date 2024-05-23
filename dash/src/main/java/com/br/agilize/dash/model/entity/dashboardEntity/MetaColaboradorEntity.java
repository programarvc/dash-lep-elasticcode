package com.br.agilize.dash.model.entity.dashboardEntity;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaEntity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class MetaColaboradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nota" )
    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "competencia_id")
    private CompetenciaEntity competencia; 
    
    @ManyToOne
    @JoinColumn(name = "colaborador_id" )
    private ColaboradorEntity colaborador;

    @Column( nullable = false, name = "data")
    private LocalDateTime data;

    @PrePersist
    public void prePersist() {
       data = LocalDateTime.now();
    } 

}
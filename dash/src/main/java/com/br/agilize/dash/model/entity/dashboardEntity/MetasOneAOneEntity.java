package com.br.agilize.dash.model.entity.dashboardEntity;

import com.br.agilize.dash.model.entity.ColaboradorEntity;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity

public class MetasOneAOneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "colaborador_id")
    private ColaboradorEntity colaborador;  

    @Column(name = "meta", columnDefinition = "TEXT")
    private String meta;

    @Column( nullable = false, name = "data")
    private LocalDate data;

    @PrePersist
    public void prePersist() {
       data = LocalDate.now();
    } 

    @PreUpdate
    public void preUpdate() {
       data = LocalDate.now();
    }

}
package com.br.agilize.dash.model.entity.dashboardEntity;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class PromptsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stack;

    @Column(nullable = false)
    private String tipo_codigo;

    @Column(nullable = false)
    private String entidade;

    @Column
    private String tabela;

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @ManyToOne
    @JoinTable(name = "promptcolaboradorentity", joinColumns = @JoinColumn(name = "prompt_id"), inverseJoinColumns = @JoinColumn(name = "colaborador_id"))
    private ColaboradorEntity colaborador;

    @ManyToOne
    @JoinTable(name = "promptesteiraentity", joinColumns = @JoinColumn(name = "prompt_id"), inverseJoinColumns = @JoinColumn(name = "esteira_id"))
    private EsteiraDeDesenvolvimentoEntity esteira;
}

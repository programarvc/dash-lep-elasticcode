package com.br.agilize.dash.model.entity.dashboardEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
public class PromptEsteiraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="prompt_id")
    private PromptsHistoryEntity prompt;

    @ManyToOne
    @JoinColumn(name="esteira_id")
    private EsteiraDeDesenvolvimentoEntity esteira;
}

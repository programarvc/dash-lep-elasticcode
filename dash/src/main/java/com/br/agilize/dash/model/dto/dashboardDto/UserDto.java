package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String nome;
    private ColaboradorDto colaborador;
    private boolean isAdmin;
}

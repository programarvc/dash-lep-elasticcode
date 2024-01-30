package com.br.agilize.dash.model.dto.dashboardDto;
import lombok.Data;

@Data
public class UserEsteiraDto {
    private Long id;
   
    private UserDto username;

    private EsteiraDeDesenvolvimentoDto esteira;
    



}
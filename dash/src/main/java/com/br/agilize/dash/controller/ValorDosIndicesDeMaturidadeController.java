package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.service.ValorDosIndicesDeMaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ValorDosIndicesDeMaturidadeController", description = "Api para gerenciar os CapacidadesRecomendadas")
@Controller
@RequestMapping("/indicesdematuridade")
public class ValorDosIndicesDeMaturidadeController extends ControllerCrudBase<ValorDosIndicesDeMaturidadeDto> {

    public ValorDosIndicesDeMaturidadeController(@Autowired ValorDosIndicesDeMaturidadeService service) {
        super(service);
    }
}
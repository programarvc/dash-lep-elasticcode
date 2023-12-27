package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.service.CapacidadesRecomendadasService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CapacidadesRecomendadasController", description = "Api para gerenciar os CapacidadesRecomendadas")
@Controller
@RequestMapping("/capacidades")
public class CapacidadesRecomendadasController extends ControllerCrudBase<CapacidadesRecomendadasDto> {

    public CapacidadesRecomendadasController(@Autowired CapacidadesRecomendadasService service) {
        super(service);
    }
}
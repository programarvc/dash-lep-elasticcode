package com.br.agilize.dash.controller.dashboardController;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.service.dashboardService.CapacidadesRecomendadasService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CapacidadesRecomendadasController", description = "Api para gerenciar os CapacidadesRecomendadas")
@Controller
@RequestMapping("/capacidades")
public class CapacidadesRecomendadasController extends ControllerCrudBase<CapacidadesRecomendadasDto> {
    
    @Autowired CapacidadesRecomendadasService service;

    public CapacidadesRecomendadasController(@Autowired CapacidadesRecomendadasService service) {
        super(service);
    }

    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<Map<String, Object>>> getCapacidadesByEsteiraId(@PathVariable Long esteiraId) {
        List<Map<String, Object>> result = service.getCapacidadesByEsteiraId(esteiraId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/latest/esteira/{esteiraId}")
    public ResponseEntity<List<Map<String, Object>>> getLatestCapacidadesByEsteiraId(@PathVariable Long esteiraId) {
        List<Map<String, Object>> capacidades = service.getLatestCapacidadesByEsteiraId(esteiraId);
        return new ResponseEntity<>(capacidades, HttpStatus.OK);
    }
}
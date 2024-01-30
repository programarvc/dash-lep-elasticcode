package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

import com.br.agilize.dash.controller.ControllerCrudBase;

import com.br.agilize.dash.model.dto.dashboardDto.TimeDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeEsteiraDto;

import com.br.agilize.dash.service.dashboardService.TimeService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ColaboradorControoler", description = "Api para gerenciar os colaboradores")
@Controller
@RequestMapping("/time")
public class TimeController extends ControllerCrudBase<TimeDto> {
    @Autowired TimeService service;

    public TimeController(@Autowired TimeService service) {
        super(service);
    }

     @Operation(summary = "Salva um objeto relacionando Time ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })

    @PostMapping("/esteira")
    public ResponseEntity<TimeEsteiraDto> salvarTimeEsteira(@RequestBody TimeEsteiraDto payload) {
        TimeEsteiraDto timeEsteiraSalvo = this.service.salvarTimeEsteira(payload);
        return ResponseEntity.ok(timeEsteiraSalvo);
    }

   
    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<Map<String, String>>> buscarNomesDosTimesEDosColaboradoresPorEsteiraId(@PathVariable Long esteiraId) {
        List<Map<String, String>> nomesDosTimesEDosColaboradores = this.service.buscarNomesDosTimesEDosColaboradoresPorEsteiraId(esteiraId);
        return ResponseEntity.ok(nomesDosTimesEDosColaboradores);
    }
    

}


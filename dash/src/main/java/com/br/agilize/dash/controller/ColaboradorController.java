package com.br.agilize.dash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.MetasOneAOneDto;
import com.br.agilize.dash.service.ColaboradorService;
import com.br.agilize.dash.service.dashboardService.MetasOneAOneService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ColaboradorControoler", description = "Api para gerenciar os colaboradores")
@Controller
@RequestMapping("/colaborador")
public class ColaboradorController extends ControllerCrudBase<ColaboradorDto> {
    @Autowired ColaboradorService service;

    @Autowired
    MetasOneAOneService metasOneAOneService;

   public ColaboradorController(@Autowired ColaboradorService service) {
        super(service);
    }


    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByEsteiraId(@PathVariable Long esteiraId) {
        List<ColaboradorDto> colaboradores = service.getColaboradoresByEsteiraId(esteiraId);
        return new ResponseEntity<>(colaboradores, HttpStatus.OK);
    }

       /*   @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByEsteiraId(@PathVariable Long esteiraId) {
        List<ColaboradorDto> colaboradores = service.getColaboradoresByEsteiraId(esteiraId);
        return new ResponseEntity<>(colaboradores, HttpStatus.OK);
    }*/

   /*  @GetMapping("/{colaboradorId}/metasOneAOne")
    public MetasOneAOneDto getLatestByColaboradorId(@PathVariable Long colaboradorId) {
        return service.findLatestByColaboradorId(colaboradorId);
    }*/
}
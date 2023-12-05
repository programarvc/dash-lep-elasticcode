package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.CompetenciaColaboradorDto;
import com.br.agilize.dash.model.dto.CompetenciaDto;
import com.br.agilize.dash.service.CompetenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CompetenciaController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/competencia")
public class CompetenciaController extends ControllerCrudBase<CompetenciaDto> {
    private CompetenciaService service;

    public CompetenciaController(@Autowired CompetenciaService service) {
        super(service);
        this.service = service;
    }

    @Operation(
            summary = "Obtem Acoes do colaborador pelo id",
            description = "Retorna as informações do objeto pelo do id",
            tags = { "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<CompetenciaColaboradorDto>> obterAcoesColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok( service.obterCompetenciaColaborador(id));
    }

    @Operation(
            summary = "Salva um objeto relacionando ação ao colaborador",
            description = "Retorna as informações do objeto que foram salvas",
            tags = { "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CompetenciaColaboradorDto> salvarAcoesColaborado(@RequestBody CompetenciaColaboradorDto payload) {
        return ResponseEntity.ok( service.salvarCompetenciaColaborador(payload));
    }

}

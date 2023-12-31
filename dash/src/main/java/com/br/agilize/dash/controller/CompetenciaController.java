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
import java.util.Map;

@Tag(name = "CompetenciaController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/competencia")
public class CompetenciaController extends ControllerCrudBase<CompetenciaDto> {
    private CompetenciaService service;

    public CompetenciaController(@Autowired CompetenciaService service) {
        super(service);
        this.service = service;
    }

    @Operation(summary = "Deleta a Competencia pelo id", description = "", tags = {
            "id", "delete" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtem competencias do colaborador pelo id", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<CompetenciaColaboradorDto>> obterCompetenciaColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterCompetenciaColaborador(id));
    }

    @Operation(summary = "Salva um objeto relacionando competencia ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CompetenciaColaboradorDto> salvarCompetenciaColaborado(
            @RequestBody CompetenciaColaboradorDto payload) {
        return ResponseEntity.ok(service.salvarCompetenciaColaborador(payload));
    }

    @DeleteMapping("/colaborador/{colaboradorId}/competencia/{competenciaId}")
    public ResponseEntity<Void> apagarCompetenciaColaborador(@PathVariable Long colaboradorId,
            @PathVariable Long competenciaId) {
        service.apagarCompetenciaColaborador(colaboradorId, competenciaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtem todas as associações", description = "Retorna uma lista de todas as associações entre empresas e colaboradores", tags = {
            "get" })
    @GetMapping("/colaboradores")
    public ResponseEntity<List<Map<String, Long>>> obterTodasCompetenciasColaboradores() {
        return ResponseEntity.ok(service.obterTodasCompetenciasColaboradores());
    }

}
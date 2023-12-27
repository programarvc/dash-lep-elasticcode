package com.br.agilize.dash.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.agilize.dash.model.dto.AcoesColaboradorDto;
import com.br.agilize.dash.model.dto.AcoesDto;
import com.br.agilize.dash.service.AcoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AcoesController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/acoes")
public class AcoesController extends ControllerCrudBase<AcoesDto> {
    private AcoesService service;

    public AcoesController(@Autowired AcoesService service) {
        super(service);
        this.service = service;
    }

    @Operation(summary = "Deleta a Empresa  pelo id", description = "", tags = {
            "id", "delete" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtem Acoes do colaborador pelo id", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<AcoesColaboradorDto>> obterAcoesColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterAcoesColaborador(id));
    }

    @Operation(summary = "Salva um objeto relacionando ação ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AcoesColaboradorDto> salvarAcoesColaborado(@RequestBody AcoesColaboradorDto payload) {
        return ResponseEntity.ok(service.salvarAcaoColaborador(payload));
    }

    @DeleteMapping("/colaborador/{colaboradorId}/acao/{id}")
    public ResponseEntity<Void> apagarAcaoColaborador(@PathVariable Long colaboradorId, @PathVariable Long id) {
        service.apagarAcaoColaborador(colaboradorId, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtem todas as associações", description = "Retorna uma lista de todas as associações entre empresas e colaboradores", tags = {
            "get" })
    @GetMapping("/colaboradores")
    public ResponseEntity<List<Map<String, Long>>> obterTodasAcoesColaboradores() {
        return ResponseEntity.ok(service.obterTodasAcoesColaborador());
    }

}
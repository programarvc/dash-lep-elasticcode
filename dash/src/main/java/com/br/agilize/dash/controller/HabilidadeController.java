package com.br.agilize.dash.controller;



import com.br.agilize.dash.model.dto.HabilidadeColaboradorDto;
import com.br.agilize.dash.model.dto.HabilidadeDto;

import com.br.agilize.dash.service.HabilidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "HabilidadeController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/habilidades")
public class HabilidadeController extends ControllerCrudBase<HabilidadeDto> {
    
    @Autowired
    private HabilidadeService service;

    public HabilidadeController(@Autowired HabilidadeService service) {
        super(service);
    }

    @Operation(summary = "Obtem Empresa do colaborador pelo id", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<HabilidadeColaboradorDto>> obterHabilidadeColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterHabilidadesColaborador(id));
    }

    @Operation(summary = "Obtem colaboradores por id da empresa", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/{id}/colaboradores")
    public ResponseEntity<List<HabilidadeColaboradorDto>> obterColaboradorPorHabilidadeId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByHabilidade(id));
    }

    @Operation(summary = "Salva um objeto relacionando ação ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HabilidadeColaboradorDto> salvarHabilidadeColaborado(@RequestBody HabilidadeColaboradorDto payload) {
        return ResponseEntity.ok(service.salvarHabilidadeColaborador(payload));
    }


}

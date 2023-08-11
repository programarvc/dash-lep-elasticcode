package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.AcoesColaboradorDto;
import com.br.agilize.dash.model.dto.AcoesDto;
import com.br.agilize.dash.model.dto.HabilitadeDto;
import com.br.agilize.dash.service.AcoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AcoesController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/acoes")
public class AcoesController extends ControllerCrudBase<AcoesDto> {
    private AcoesService service;

    public AcoesController(@Autowired AcoesService service) {
        super(service);
        this.service = service;
    }


    @Operation(
            summary = "Obtem Acoes do colaborador pelo id",
            description = "Retorna as informações do objeto pelo do id",
            tags = { "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<AcoesColaboradorDto>> obterAcoesColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok( service.obterAcoesColaborador(id));
    }

    @Operation(
            summary = "Salva um objeto relacionando ação ao colaborador",
            description = "Retorna as informações do objeto que foram salvas",
            tags = { "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AcoesColaboradorDto> salvarAcoesColaborado(@RequestBody AcoesColaboradorDto payload) {
        return ResponseEntity.ok( service.salvarAcaoColaborador(payload));
    }

}

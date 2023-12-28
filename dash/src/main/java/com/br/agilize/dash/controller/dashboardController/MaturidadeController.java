package com.br.agilize.dash.controller.dashboardController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.MaturidadeDto;
import com.br.agilize.dash.service.dashboardService.MaturidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MaturidadeController", description = "Api para gerenciar os Maturidades")
@Controller
@RequestMapping("/maturidade")
public class MaturidadeController extends ControllerCrudBase<MaturidadeDto> {

    @Autowired
    MaturidadeService service;

    public MaturidadeController(@Autowired MaturidadeService service) {
        super(service);
    }

    @Operation(summary = "Obtem maturidades por id da esteira", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<MaturidadeDto>> obterMaturidadePorEsteiraId(@PathVariable Long esteiraId) {
        return ResponseEntity.ok(((MaturidadeService) service).obterPorEsteiraId(esteiraId));
    }

}
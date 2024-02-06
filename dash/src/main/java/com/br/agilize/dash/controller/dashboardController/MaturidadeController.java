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
import com.br.agilize.dash.model.dto.dashboardDto.MaturidadeDto;
import com.br.agilize.dash.service.dashboardService.MaturidadeService;

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

    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<MaturidadeDto>> getMaturidadeByEsteiraId(@PathVariable Long esteiraId) {
        List<MaturidadeDto> maturidades = service.getMaturidadeByEsteiraId(esteiraId);
        return new ResponseEntity<>(maturidades, HttpStatus.OK);
    }

   @GetMapping("/latest/esteira/{esteiraId}")
    public ResponseEntity<Map<String, Object>> getLatestMaturidadeByEsteiraId(@PathVariable Long esteiraId) {
        Map<String, Object> maturidade = service.getLatestMaturidadeByEsteiraId(esteiraId);
        return new ResponseEntity<>(maturidade, HttpStatus.OK);
    }

}


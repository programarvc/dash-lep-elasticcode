package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.service.dashboardService.JornadaDeTransformacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "JornadaDeTransformacaoController", description = "Api para gerenciar os JornadaDeTransformacaos")
@Controller
@RequestMapping("/jornada")
public class JornadaDeTransformacaoController extends ControllerCrudBase<JornadaDeTransformacaoDto> {

    @Autowired JornadaDeTransformacaoService service;

    public JornadaDeTransformacaoController(@Autowired JornadaDeTransformacaoService service) {
        super(service);
    }

    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<Map<String, Object>> getJornadasByEsteiraId(@PathVariable Long esteiraId) {
        Map<String, Object>result = service.getJornadasByEsteiraId(esteiraId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
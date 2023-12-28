package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.service.dashboardService.JornadaDeTransformacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "JornadaDeTransformacaoController", description = "Api para gerenciar os JornadaDeTransformacaos")
@Controller
@RequestMapping("/jornada")
public class JornadaDeTransformacaoController extends ControllerCrudBase<JornadaDeTransformacaoDto> {

    public JornadaDeTransformacaoController(@Autowired JornadaDeTransformacaoService service) {
        super(service);
    }
}
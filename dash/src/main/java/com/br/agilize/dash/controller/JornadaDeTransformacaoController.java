package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.service.JornadaDeTransformacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "JornadaDeTransformacaoController", description = "Api para gerenciar os JornadaDeTransformacaos")
@Controller
@RequestMapping("/jornada")
public class JornadaDeTransformacaoController extends ControllerCrudBase<JornadaDeTransformacaoDto> {

    public JornadaDeTransformacaoController(@Autowired JornadaDeTransformacaoService service) {
        super(service);
    }
}
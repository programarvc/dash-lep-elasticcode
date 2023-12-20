package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.MaturidadeDto;
import com.br.agilize.dash.service.MaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MaturidadeController", description = "Api para gerenciar os Maturidades")
@Controller
@RequestMapping("/maturidade")
public class MaturidadeController extends ControllerCrudBase<MaturidadeDto> {

    public MaturidadeController(@Autowired MaturidadeService service) {
        super(service);
    }
}
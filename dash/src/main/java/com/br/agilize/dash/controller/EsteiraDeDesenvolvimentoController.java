package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.service.EsteiraDeDesenvolvimentoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "EsteiraDeDesenvolvimentoController", description = "Api para gerenciar os EsteiraDeDesenvolvimentos")
@Controller
@RequestMapping("/esteira")
public class EsteiraDeDesenvolvimentoController extends ControllerCrudBase<EsteiraDeDesenvolvimentoDto> {

    public EsteiraDeDesenvolvimentoController(@Autowired EsteiraDeDesenvolvimentoService service) {
        super(service);
    }
}
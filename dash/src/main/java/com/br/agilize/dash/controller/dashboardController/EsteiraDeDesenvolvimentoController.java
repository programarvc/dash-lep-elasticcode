package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.service.dashboardService.EsteiraDeDesenvolvimentoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "EsteiraDeDesenvolvimentoController", description = "Api para gerenciar os EsteiraDeDesenvolvimentos")
@Controller
@RequestMapping("/esteira")
public class EsteiraDeDesenvolvimentoController extends ControllerCrudBase<EsteiraDeDesenvolvimentoDto> {
     
    @Autowired 
    EsteiraDeDesenvolvimentoService service;

    public EsteiraDeDesenvolvimentoController(@Autowired EsteiraDeDesenvolvimentoService service) {
        super(service);
    }



}

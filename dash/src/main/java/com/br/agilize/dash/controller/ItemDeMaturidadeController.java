package com.br.agilize.dash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.model.dto.ItemDeMaturidadeDto;
import com.br.agilize.dash.service.ItemDeMaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ItemDeMaturidadeController", description = "Api para gerenciar os ItemDeMaturidades")
@Controller
@RequestMapping("/itemdematuridade")
public class ItemDeMaturidadeController extends ControllerCrudBase<ItemDeMaturidadeDto> {

    public ItemDeMaturidadeController(@Autowired ItemDeMaturidadeService service) {
        super(service);
    }
}
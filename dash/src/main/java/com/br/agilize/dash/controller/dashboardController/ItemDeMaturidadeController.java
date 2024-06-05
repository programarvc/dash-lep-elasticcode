package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.ItemDeMaturidadeDto;
import com.br.agilize.dash.service.dashboardService.ItemDeMaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ItemDeMaturidadeController", description = "Api para gerenciar os ItemDeMaturidades")
@CrossOrigin(origins = "https://dash.elasticcode.com.br")
@Controller
@RequestMapping("/itemdematuridade")
public class ItemDeMaturidadeController extends ControllerCrudBase<ItemDeMaturidadeDto> {

    public ItemDeMaturidadeController(@Autowired ItemDeMaturidadeService service) {
        super(service);
    }
}
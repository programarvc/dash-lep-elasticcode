package com.br.agilize.dash.controller.dashboardController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;
import com.br.agilize.dash.service.dashboardService.ItemDeMaturidadeService;
import com.br.agilize.dash.service.dashboardService.ValorDosIndicesDeMaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ValorDosIndicesDeMaturidadeController", description = "Api para gerenciar os ValorDosIndicesDeMaturidade")
@Controller
@RequestMapping("/indicesdematuridade")
public class ValorDosIndicesDeMaturidadeController extends ControllerCrudBase<ValorDosIndicesDeMaturidadeDto> {

    @Autowired ValorDosIndicesDeMaturidadeService service;
    @Autowired
    ItemDeMaturidadeService itemDeMaturidadeService;

    public ValorDosIndicesDeMaturidadeController(@Autowired ValorDosIndicesDeMaturidadeService service) {
        super(service);
    }

    @GetMapping("item/{itemDeMaturidadeId}")
    public ResponseEntity<List<Object[]>> getValoresAndNome(@PathVariable Long itemDeMaturidadeId) {
        List<Object[]> result = service.getValoresAndNome(itemDeMaturidadeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("tipo/{tipoMaturidade}")
    public ResponseEntity<List<Object[]>> getValoresAndNome(@PathVariable TiposMaturidadeEnum tipoMaturidade) {
        List<Object[]> result = service.getValoresAndNome(tipoMaturidade);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
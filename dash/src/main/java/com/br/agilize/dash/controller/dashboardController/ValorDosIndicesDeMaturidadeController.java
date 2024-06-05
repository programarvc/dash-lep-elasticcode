package com.br.agilize.dash.controller.dashboardController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;
import com.br.agilize.dash.service.dashboardService.ItemDeMaturidadeService;
import com.br.agilize.dash.service.dashboardService.ValorDosIndicesDeMaturidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ValorDosIndicesDeMaturidadeController", description = "Api para gerenciar os ValorDosIndicesDeMaturidade")
@CrossOrigin(origins = "https://dash.elasticcode.com.br")
@Controller
@RequestMapping("/indicesdematuridade")
public class ValorDosIndicesDeMaturidadeController extends ControllerCrudBase<ValorDosIndicesDeMaturidadeDto> {

    @Autowired
    ValorDosIndicesDeMaturidadeService service;
    @Autowired
    ItemDeMaturidadeService itemDeMaturidadeService;

    public ValorDosIndicesDeMaturidadeController(@Autowired ValorDosIndicesDeMaturidadeService service) {
        super(service);
    }

    @GetMapping("/esteira/{esteiraId}/tipo/{tipoMaturidade}")
    public ResponseEntity<List<Map<String, Object>>> getValoresByEsteiraIdAndTipoMaturidade(@PathVariable Long esteiraId, @PathVariable TiposMaturidadeEnum tipoMaturidade) {
        List<Map<String, Object>> result = service.getValoresByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

@GetMapping("/esteira/{esteiraId}/tipo/{tipoMaturidade}/latest")
public ResponseEntity<List<ValorDosIndicesDeMaturidadeDto>> getValoresByEsteiraIdAndTipoMaturidadeLatest(@PathVariable Long esteiraId, @PathVariable TiposMaturidadeEnum tipoMaturidade) {
    List<ValorDosIndicesDeMaturidadeDto> result = service.buscarDadosAtualizados(esteiraId, tipoMaturidade);
    return new ResponseEntity<>(result, HttpStatus.OK);
}


    /*@GetMapping("/itemdematuridade/latest/esteira/{esteiraId}")
    public ResponseEntity<String> getLatestItemDeMaturidadeByEsteiraId(@PathVariable Long esteiraId) {
        String itemName = service.getLatestItemDeMaturidadeByEsteiraId(esteiraId);
        return new ResponseEntity<>(itemName, HttpStatus.OK);
    }*/

  
    @GetMapping("/itemdematuridade/latest/esteira/{esteiraId}")
    public ResponseEntity<List<String>> getLatestItemDeMaturidadeByEsteiraId(@PathVariable Long esteiraId) {
        List<String> itemNames = service.getLatestItemDeMaturidadeByEsteiraId(esteiraId);
        return new ResponseEntity<>(itemNames, HttpStatus.OK);
    }
    


    //retorna os dados de acordo com a data de maturidade id em ordem cresente
@GetMapping("/maturidade/{maturidadeId}")
public ResponseEntity<List<Object[]>> findByMaturidadeId(@PathVariable Long maturidadeId) {
    List<Object[]> entities = service.findByMaturidadeId(maturidadeId);
    return ResponseEntity.ok(entities);
}

@GetMapping("/allmaturidade/{maturidadeId}")
public ResponseEntity<List<ValorDosIndicesDeMaturidadeDto>> getMaturidade(@PathVariable Long maturidadeId) {
    List<ValorDosIndicesDeMaturidadeDto> entities = service.findValorDoIndicesByMaturidadeId(maturidadeId);
    return ResponseEntity.ok(entities);
}
}
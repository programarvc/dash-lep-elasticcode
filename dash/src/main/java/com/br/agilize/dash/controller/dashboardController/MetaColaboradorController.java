package com.br.agilize.dash.controller.dashboardController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.entity.dashboardEntity.MetaColaboradorEntity;
import com.br.agilize.dash.model.dto.dashboardDto.MetaColaboradorDto;
import com.br.agilize.dash.service.dashboardService.MetaColaboradorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MetasOneAOneController", description = "Api para gerenciar os MetasOneAOne")
@Controller
@RequestMapping("/metas")
public class MetaColaboradorController extends ControllerCrudBase<MetaColaboradorDto> {
     
    @Autowired 
    MetaColaboradorService service;

    public MetaColaboradorController(@Autowired MetaColaboradorService service) {
        super(service);
    }


/* 
     @PostMapping("/colaborador")
    public ResponseEntity<MetaColaboradorDto> salvarMetasColaborador(@RequestBody MetaColaboradorDto payload) {
        MetaColaboradorDto savedMetasColaborador = service.salvarMetasColaborador(payload);
        return ResponseEntity.ok(savedMetasColaborador);
    }


    @GetMapping("/colaborador/{colaboradorId}")
  public ResponseEntity<MetaColaboradorDto> getLatestMetaByColaboradorId(@PathVariable Long colaboradorId) {
    MetaColaboradorDto recentMeta = service.findRecentMetaByColaboradorId(colaboradorId);
    return new ResponseEntity<>(recentMeta, HttpStatus.OK);
    }

    @GetMapping("/colaborador/{colaboradorId}/all")
    public ResponseEntity<List<MetaColaboradorDto>> getAllMetasByColaboradorId(@PathVariable Long colaboradorId) {
        List<MetasColaboradorDto> metas = service.findAllMetasByColaboradorId(colaboradorId);
        return new ResponseEntity<>(metas, HttpStatus.OK);
    }

    */
}

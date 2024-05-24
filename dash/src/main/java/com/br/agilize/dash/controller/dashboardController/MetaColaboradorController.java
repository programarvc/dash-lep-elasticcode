package com.br.agilize.dash.controller.dashboardController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

     @PostMapping
    public ResponseEntity<MetaColaboradorDto> salvar(@RequestBody MetaColaboradorDto payload) {
        MetaColaboradorDto savedMeta = service.salvar(payload);
        return ResponseEntity.ok(savedMeta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3meta/{colaboradorId}")
    @ResponseBody
    public List<Map<String, Object>> getTop3CompetenciasByColaborador(@PathVariable Long colaboradorId) {
        return service.getTop3CompetenciasByColaborador(colaboradorId);
    }

    @GetMapping("/datameta/{colaboradorId}")
    @ResponseBody
    public Map<LocalDate, List<Map<String, Object>>> getCompetenciasWithAtLeast3Competencias(@PathVariable Long colaboradorId) {
        return service.getCompetenciasWithAtLeast3Competencias(colaboradorId);
    }

    @GetMapping("/datas/{colaboradorId}")
    @ResponseBody
    public List<LocalDate> getDatasWithAtLeast3Competencias(@PathVariable Long colaboradorId) {
        return service.getDatasWithAtLeast3Competencias(colaboradorId);
    }

    @GetMapping("/competencias/{colaboradorId}")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> getTop3CompetenciasByColaboradorAndData(
            @PathVariable Long colaboradorId, 
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        return service.getTop3CompetenciasByColaboradorAndData(colaboradorId, data);
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

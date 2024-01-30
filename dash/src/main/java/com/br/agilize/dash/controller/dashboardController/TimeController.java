package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeDto;
import com.br.agilize.dash.service.dashboardService.TimeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ColaboradorControoler", description = "Api para gerenciar os colaboradores")
@Controller
@RequestMapping("/time")
public class TimeController extends ControllerCrudBase<TimeDto> {
    @Autowired TimeService service;

    public TimeController(@Autowired TimeService service) {
        super(service);
    }

   
   /* @GetMapping("/esteira/{esteiraId}/colaboradores")
    public ResponseEntity<List<TimeColaboradorDto>> findTimeAndColaboradorByEsteiraId(@PathVariable Long esteiraId) {
        List<TimeColaboradorDto> timeAndColaboradores = service.findTimeAndColaboradorByEsteiraId(esteiraId);
        return new ResponseEntity<>(timeAndColaboradores, HttpStatus.OK);
    }
    }*/

    @PostMapping("/colaborador")
    public ResponseEntity<TimeColaboradorDto> salvarTimeColaborador(@RequestBody TimeColaboradorDto payload) {
        TimeColaboradorDto savedTimeColaborador = service.salvarTimeColaborador(payload);
        return ResponseEntity.ok(savedTimeColaborador);
    }


  @GetMapping("/esteira/{esteiraId}/colaborador/{colaboradorId}")
    public ResponseEntity<List<TimeDto>> getTimesByEsteiraIdAndColaboradorId(@PathVariable Long esteiraId, @PathVariable Long colaboradorId) {
        List<TimeDto> times = service.findTimesByEsteiraIdAndColaboradorId(esteiraId, colaboradorId);
        return ResponseEntity.ok(times);
    }

    @GetMapping("/esteira/{esteiraId}/time/{timeId}")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByEsteiraIdAndTimeId(@PathVariable Long esteiraId, @PathVariable Long timeId) {
        List<ColaboradorDto> colaboradores = service.findColaboradoresByEsteiraIdAndTimeId(esteiraId, timeId);
        return ResponseEntity.ok(colaboradores);
    }


    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByEsteiraId(@PathVariable Long esteiraId) {
        List<ColaboradorDto> colaboradores = service.getColaboradoresByEsteiraId(esteiraId);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/esteira/{esteiraId}/times")
    public ResponseEntity<List<Object[]>> getTimesByEsteiraId(@PathVariable Long esteiraId) {
        List<Object[]> times = service.getTimesByEsteiraId(esteiraId);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/{timeId}/colaboradores")
    public ResponseEntity<List<Object[]>> getColaboradoresByTimeId(@PathVariable Long timeId) {
        List<Object[]> colaboradores = service.findColaboradoresByTimeId(timeId);
        return new ResponseEntity<>(colaboradores, HttpStatus.OK);
    }

}


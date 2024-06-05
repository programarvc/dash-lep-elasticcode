package com.br.agilize.dash.controller.dashboardController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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


@GetMapping("/colaborador/esteira/{esteiraId}")
public ResponseEntity<List<TimeColaboradorDto>> getTimeAndColaboradorByEsteiraId(@PathVariable Long esteiraId) {
    List<TimeColaboradorDto> timeColaborador = service.getTimeAndColaboradorByEsteiraId(esteiraId);
    return ResponseEntity.ok(timeColaborador);
}


    @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByEsteiraId(@PathVariable Long esteiraId) {
        List<ColaboradorDto> colaboradores = service.getColaboradoresByEsteiraId(esteiraId);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/esteira/{esteiraId}/times")
    public ResponseEntity<List<TimeDto>> getTimesByEsteiraId(@PathVariable Long esteiraId) {
        List<TimeDto> times = service.getTimesByEsteiraId(esteiraId);
        return ResponseEntity.ok(times);
    }

    @GetMapping("/{timeId}/colaboradores")
    public ResponseEntity<List<ColaboradorDto>> getColaboradoresByTimeId(@PathVariable Long timeId) {
        List<ColaboradorDto> colaboradores = service.findColaboradoresByTimeId(timeId);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/colaboradorId/{colaboradorId}")
    public ResponseEntity<List<TimeDto>> getTimesAndEsteiraByColaboradorId(@PathVariable Long colaboradorId) {
        List<TimeDto> timeColaborador = service.getTimesByColaboradorId(colaboradorId);
        return ResponseEntity.ok(timeColaborador);
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<TimeColaboradorDto>> findTimeColaboradorByColaboradorId(@PathVariable Long colaboradorId) {
        List<TimeColaboradorDto> timeColaboradores = service.findTimeColaboradorByColaboradorId(colaboradorId);
        return ResponseEntity.ok(timeColaboradores);
    }

}


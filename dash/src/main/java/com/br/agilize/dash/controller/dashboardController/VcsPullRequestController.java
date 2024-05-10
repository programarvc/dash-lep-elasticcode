package com.br.agilize.dash.controller.dashboardController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.br.agilize.dash.model.dto.dashboardDto.PrCountDto;
import com.br.agilize.dash.service.dashboardService.VcsPullRequestService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "VcsPullRequestController", description = "Api para gerenciar os VcsPullRequests")
@Controller
@RequestMapping("/prcount")
public class VcsPullRequestController {
     
    @Autowired 
    VcsPullRequestService service;

    /* 
    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity <PrCountDto> getPrCountByColaboradorId(@PathVariable Long colaboradorId) {
        PrCountDto prCountDto = service.getPrCountByColaboradorId(colaboradorId);
        return ResponseEntity.ok(prCountDto);
    }
    */

    @GetMapping("/last1year/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast1YearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast1YearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    @GetMapping("/thisyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountThisYearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountThisYearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    @GetMapping("/lastyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLastYearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLastYearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    @GetMapping("/last30days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast30DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast30DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    @GetMapping("/last7days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast7DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast7DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

}

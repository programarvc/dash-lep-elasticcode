package com.br.agilize.dash.controller.dashboardController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // Query para buscar a quantidade total de PRs de um colaborador por id dentro de um ano
    @GetMapping("/last1year/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast1YearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast1YearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    // Query para buscar a quantidade total de PRs de um colaborador por id esse ano
    @GetMapping("/thisyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountThisYearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountThisYearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    // Query para buscar a quantidade total de PRs de um colaborador por id ano passado
    @GetMapping("/lastyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLastYearForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLastYearForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 90 dias
    @GetMapping("/last90days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast90DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast90DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 60 dias
    @GetMapping("/last60days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast60DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast60DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    
    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 30 dias
    @GetMapping("/last30days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast30DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast30DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    
    // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 7 dias
    @GetMapping("/last7days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast7DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast7DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    // Query para buscar a todos  PRs de um colaborador por id 
    @GetMapping("/allprs/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getAllPrCountForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getAllPrCountForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    @GetMapping("/dates/{colaboradorId}")
    @ResponseBody
    public Map<String, Object> getPrCountForColaborador(
            @PathVariable Long colaboradorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return service.getPrCountForColaborador(colaboradorId, startDate, endDate);
    }

      // Endpoint para buscar a quantidade total de PRs nos últimos 30, 60 e 90 dias
      @GetMapping("/allprsdays")
      public ResponseEntity<Map<String, Object>> getPrCountLast30And60And90Days() {
          Map<String, Object> prCount = service.getPrCountLast30And60And90Days();
          return ResponseEntity.ok(prCount);
      }
    // End poit para buscar os 5 colaboradores com mais PRs
    @GetMapping("/top5Colaboradores")
    public ResponseEntity<List<Map<String, Object>>> getTop5ColaboradoresByPrs() {
        List<Map<String, Object>> top5Colaboradores = service.getTop5ColaboradoresByPrs();
        return ResponseEntity.ok(top5Colaboradores);
    }

    // End point para buscar a quantidade total de PRs
    @GetMapping("/totalprs")
    public ResponseEntity<List<Map<String, Object>>> getTotalPrs() {
        List<Map<String, Object>> totalPrs = service.getTotalPrs();
        return new ResponseEntity<>(totalPrs, HttpStatus.OK);
    }
}

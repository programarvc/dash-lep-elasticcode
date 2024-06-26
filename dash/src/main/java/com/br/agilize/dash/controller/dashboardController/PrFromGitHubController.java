package com.br.agilize.dash.controller.dashboardController;
/* 
//End point para buscar dados de PRs do GitHub total, últimos 90, 30 e 7 dias
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.br.agilize.dash.service.dashboardService.PrsGitHubApiService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PrFromGitHubController", description = "Api para gerenciar os PrFromGitHub")
@Controller
@RequestMapping("/prgithub")
public class PrFromGitHubController {
     
    @Autowired 
    PrsGitHubApiService service;

    // End-point de busca quantidade de PRs de um colaborador por id
    @GetMapping("/count/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    // End-point de busca quantidade de PRs de um colaborador por id nos últimos 90 dias
    @GetMapping("/last90days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast90DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast90DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }

    // End-point de busca quantidade de PRs de um colaborador por id nos últimos 30 dias
    @GetMapping("/last30days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast30DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast30DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    }
    // End-point de busca quantidade de PRs de um colaborador por id nos últimos 7 dias
    @GetMapping("/last7days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountLast7DaysForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> prCount = service.getPrCountLast7DaysForColaborador(colaboradorId);
        return ResponseEntity.ok(prCount);
    } 
}
*/

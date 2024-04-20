package com.br.agilize.dash.controller.dashboardController;

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

    @GetMapping("/count/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> getPrCountForColaborador(@PathVariable Long colaboradorId) {
        Map<String, Object> Count = service.getPrCountForColaborador(colaboradorId);
        return ResponseEntity.ok(Count);
    }
}

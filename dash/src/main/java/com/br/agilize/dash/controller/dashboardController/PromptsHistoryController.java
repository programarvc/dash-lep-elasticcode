package com.br.agilize.dash.controller.dashboardController;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;
import com.br.agilize.dash.service.dashboardService.PromptsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prompts-history")
public class PromptsHistoryController extends ControllerCrudBase<PromptsHistoryDto> {

    @Autowired
    PromptsHistoryService service;

    public PromptsHistoryController(@Autowired PromptsHistoryService service) {
        super(service);
    }

    @GetMapping("/count/user-esteira/{userEsteiraId}")
    public ResponseEntity<Long> countByUserEsteiraId(@PathVariable Long userEsteiraId) {
        Long count = this.service.countByUserEsteiraId(userEsteiraId);
        return ResponseEntity.ok(count);
    }
}
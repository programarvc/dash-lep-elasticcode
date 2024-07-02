package com.br.agilize.dash.controller.dashboardController;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;
import com.br.agilize.dash.service.dashboardService.PromptsHistoryService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user-esteira/{userEsteiraId}")
    public ResponseEntity<List<PromptsHistoryDto>> findByUserEsteiraId(@PathVariable Long userEsteiraId) {
        List<PromptsHistoryDto> promptsHistories = this.service.findByUserEsteiraId(userEsteiraId);
        return ResponseEntity.ok(promptsHistories);
    }
}
package com.br.agilize.dash.controller.dashboardController;

import java.util.*;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.agilize.dash.controller.ControllerCrudBase;
import com.br.agilize.dash.model.dto.dashboardDto.UserDto;
import com.br.agilize.dash.model.dto.dashboardDto.UserEsteiraDto;
import com.br.agilize.dash.service.dashboardService.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ColaboradorControoler", description = "Api para gerenciar os colaboradores")
@Controller
@RequestMapping("/user")
public class UserController extends ControllerCrudBase<UserDto> {
    
    @Autowired UserService service;

    public UserController(@Autowired UserService service) {
        super(service);
    }

    @PostMapping("/esteira")
    public ResponseEntity<UserEsteiraDto> salvarUserEsteira(@RequestBody UserEsteiraDto payload) {
        UserEsteiraDto userEsteiraSalvo = this.service.salvarUserEsteira(payload);
        return ResponseEntity.ok(userEsteiraSalvo);
    }

     @GetMapping("/esteira/{esteiraId}")
    public ResponseEntity<List<Map<String, String>>> buscarNomesDosUsersPorEsteiraId(@PathVariable Long esteiraId) {
        List<Map<String, String>> nomesDosUsers = this.service.buscarNomesDosUsersPorEsteiraId(esteiraId);
        return ResponseEntity.ok(nomesDosUsers);
    }

    

    @GetMapping("/useresteira")
    public ResponseEntity<List<Map<String, Object>>> getEsteiraIdAndUsername() {
        List<Map<String, Object>> result = this.service.getEsteiraIdAndUsername();
        return ResponseEntity.ok(result);
    }

}
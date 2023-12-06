package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.EmpresaColaboradorDto;
import com.br.agilize.dash.model.dto.EmpresaDto;
import com.br.agilize.dash.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController extends ControllerCrudBase<EmpresaDto> {

    private EmpresaService service;

    public EmpresaController(@Autowired EmpresaService service) {
        super(service);
        this.service = service;
    }

    @Operation(summary = "Obtem Empresa do colaborador pelo id", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/colaborador/{id}")
    public ResponseEntity<List<EmpresaColaboradorDto>> obterEmpresasColaboradorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterEmpresasColaborador(id));
    }

    @Operation(summary = "Obtem colaboradores por id da empresa", description = "Retorna as informações do objeto pelo do id", tags = {
            "id", "get" })
    @GetMapping("/{id}/colaboradores")
    public ResponseEntity<List<EmpresaColaboradorDto>> obterColaboradorPorEmpresaId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByEmpresa(id));
    }

    @Operation(summary = "Salva um objeto relacionando ação ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmpresaColaboradorDto> salvarEmpresasColaborado(@RequestBody EmpresaColaboradorDto payload) {
        return ResponseEntity.ok(service.salvarEmpresasColaborador(payload));
    }
}

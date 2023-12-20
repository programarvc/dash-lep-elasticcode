package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.EmpresaColaboradorDto;
import com.br.agilize.dash.model.dto.EmpresaDto;
import com.br.agilize.dash.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Map;

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

    @Operation(summary = "Deleta a Empresa  pelo id", description = "", tags = {
            "id", "delete" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
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

    @Operation(summary = "Obtem todas as associações entre empresas e colaboradores para uma empresa específica", description = "Retorna uma lista de associações entre empresas e colaboradores para a empresa com o id fornecido", tags = {
            "id", "get" })
    @GetMapping("/empresa/{id}/colaboradores")
    public ResponseEntity<List<EmpresaColaboradorDto>> obterEmpresasColaboradorAll(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterEmpresasColaboradorAll(id));
    }

    @Operation(summary = "Obtem todas as associações", description = "Retorna uma lista de todas as associações entre empresas e colaboradores", tags = {
            "get" })
    @GetMapping("/colaboradores")
    public ResponseEntity<List<Map<String, Long>>> obterTodasEmpresasColaboradores() {
        return ResponseEntity.ok(service.obterTodasEmpresasColaboradores());
    }

    @Operation(summary = "Salva um objeto relacionando empresa ao colaborador", description = "Retorna as informações do objeto que foram salvas", tags = {
            "salvar", "post" })
    @PostMapping("/colaborador")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmpresaColaboradorDto> salvarEmpresasColaborado(@RequestBody EmpresaColaboradorDto payload) {
        return ResponseEntity.ok(service.salvarEmpresasColaborador(payload));
    }

    @DeleteMapping("/colaborador/{colaboradorId}/empresa/{empresaId}")
    public ResponseEntity<Void> apagarEmpresaColaborador(@PathVariable Long colaboradorId,
            @PathVariable Long empresaId) {
        service.apagarEmpresaColaborador(colaboradorId, empresaId);
        return ResponseEntity.noContent().build();
    }

}

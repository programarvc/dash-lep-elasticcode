package com.br.agilize.dash.controller;

import com.br.agilize.dash.service.ServiceCrudBase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ControllerCrudBase<T> {
    private ServiceCrudBase service;

    public ControllerCrudBase(ServiceCrudBase service) {
        this.service = service;
    }

    @Operation(
            summary = "Obtem por id",
            description = "Retorna as informações do objeto pelo do id",
            tags = { "id", "get" })
    @GetMapping("/{id}")
    public ResponseEntity<T> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok( (T) service.obterPorId(id));
    }

    @Operation(
            summary = "Obtem todos",
            description = "Retorna as informações de todos os objetos",
            tags = { "todos", "get" })
    @GetMapping
    public  ResponseEntity<List<T>> obterTodos() {
        return ResponseEntity.ok( (List<T>) service.obterTodos());
    }


    @Operation(
            summary = "Salva um objeto",
            description = "Retorna as informações do objeto que foram salvas",
            tags = { "salvar", "post" })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<T> salvar(@RequestBody T payload) {
        return ResponseEntity.ok((T) service.salvar(payload));
    }

    @Operation(
            summary = "deleta por id",
            description = "Deleta as informações do objeto pelo do id",
            tags = { "id", "delete" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}

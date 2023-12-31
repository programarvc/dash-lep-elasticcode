package com.br.agilize.dash.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.agilize.dash.service.ServiceCrudBase;

import io.swagger.v3.oas.annotations.Operation;

public abstract class ControllerCrudBase<T> {
    private ServiceCrudBase<T> service;

    public ControllerCrudBase(ServiceCrudBase<T> service) {
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

package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ValorDosIndicesDeMaturidadeMapper;
import com.br.agilize.dash.model.dto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.repository.ValorDosIndicesDeMaturidadeRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ValorDosIndicesDeMaturidadeService extends ServiceCrudBase<ValorDosIndicesDeMaturidadeDto> {
    @Autowired
    private ValorDosIndicesDeMaturidadeRepository repository;

    @Autowired
    private ValorDosIndicesDeMaturidadeMapper mapper;

    @Override
    public ValorDosIndicesDeMaturidadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<ValorDosIndicesDeMaturidadeDto> obterTodos() {
        List<ValorDosIndicesDeMaturidadeEntity> ValorDosIndicesDeMaturidade = this.repository.findAll();

        return ValorDosIndicesDeMaturidade.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public ValorDosIndicesDeMaturidadeDto salvar(ValorDosIndicesDeMaturidadeDto payload) {
        ValorDosIndicesDeMaturidadeEntity ValorDosIndicesDeMaturidadeSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(ValorDosIndicesDeMaturidadeSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
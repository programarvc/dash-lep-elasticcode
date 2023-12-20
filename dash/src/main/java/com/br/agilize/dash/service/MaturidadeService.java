package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.MaturidadeMapper;
import com.br.agilize.dash.model.dto.MaturidadeDto;
import com.br.agilize.dash.model.entity.MaturidadeEntity;
import com.br.agilize.dash.repository.MaturidadeRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MaturidadeService extends ServiceCrudBase<MaturidadeDto> {
    @Autowired
    private MaturidadeRepository repository;

    @Autowired
    private MaturidadeMapper mapper;

    @Override
    public MaturidadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<MaturidadeDto> obterTodos() {
        List<MaturidadeEntity> Maturidades = this.repository.findAll();

        return Maturidades.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public MaturidadeDto salvar(MaturidadeDto payload) {
        MaturidadeEntity MaturidadeSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(MaturidadeSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
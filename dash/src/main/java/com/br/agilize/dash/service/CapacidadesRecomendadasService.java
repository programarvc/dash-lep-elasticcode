package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.CapacidadesRecomendadasMapper;
import com.br.agilize.dash.model.dto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.entity.CapacidadesRecomendadasEntity;
import com.br.agilize.dash.repository.CapacidadesRecomendadasRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CapacidadesRecomendadasService extends ServiceCrudBase<CapacidadesRecomendadasDto> {
    @Autowired
    private CapacidadesRecomendadasRepository repository;

    @Autowired
    private CapacidadesRecomendadasMapper mapper;

    @Override
    public CapacidadesRecomendadasDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<CapacidadesRecomendadasDto> obterTodos() {
        List<CapacidadesRecomendadasEntity> CapacidadesRecomendadas = this.repository.findAll();

        return CapacidadesRecomendadas.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public CapacidadesRecomendadasDto salvar(CapacidadesRecomendadasDto payload) {
        CapacidadesRecomendadasEntity CapacidadesRecomendadasSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(CapacidadesRecomendadasSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
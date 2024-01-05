package com.br.agilize.dash.service.dashboardService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.CapacidadesRecomendadasMapper;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;
import com.br.agilize.dash.repository.dashboardRepository.CapacidadesRecomendadasRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

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

    public List<Map<String, Object>> getCapacidadesByEsteiraId(Long esteiraId) {
        return repository.findByEsteiraId(esteiraId);
    }
}
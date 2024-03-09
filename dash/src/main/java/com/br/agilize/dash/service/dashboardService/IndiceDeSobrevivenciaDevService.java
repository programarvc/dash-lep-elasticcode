package com.br.agilize.dash.service.dashboardService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.IndiceDeSobrevivenciaDevMapper;
import com.br.agilize.dash.model.dto.dashboardDto.IndiceDeSobrevivenciaDevDto;
import com.br.agilize.dash.model.entity.dashboardEntity.IndiceDeSobrevivenciaDevEntity;
import com.br.agilize.dash.repository.dashboardRepository.IndiceDeSobrevivenciaDevRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IndiceDeSobrevivenciaDevService extends ServiceCrudBase<IndiceDeSobrevivenciaDevDto> {
    @Autowired
    private IndiceDeSobrevivenciaDevRepository repository;

    @Autowired
    private IndiceDeSobrevivenciaDevMapper mapper;

    @Override
    public IndiceDeSobrevivenciaDevDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<IndiceDeSobrevivenciaDevDto> obterTodos() {
        List<IndiceDeSobrevivenciaDevEntity> IndiceDeSobrevivenciaDev = this.repository.findAll();

        return IndiceDeSobrevivenciaDev.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public IndiceDeSobrevivenciaDevDto salvar(IndiceDeSobrevivenciaDevDto payload) {
        IndiceDeSobrevivenciaDevEntity IndiceDeSobrevivenciaDevSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(IndiceDeSobrevivenciaDevSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public Map<String, Object> obterValorIndicePorIdColaborador(Long colaboradorId) {
        Map<String, Object> valorIndice = this.repository.findValorIndiceByColaboradorId(colaboradorId);
        if (valorIndice == null || valorIndice.isEmpty()) {
            valorIndice = new HashMap<>();
            valorIndice.put("valorIndice", 0);
        }
        return valorIndice;
    }
}
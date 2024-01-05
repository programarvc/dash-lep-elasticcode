package com.br.agilize.dash.service.dashboardService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.ValorDosIndicesDeMaturidadeMapper;
import com.br.agilize.dash.model.dto.dashboardDto.ValorDosIndicesDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;
import com.br.agilize.dash.repository.dashboardRepository.ValorDosIndicesDeMaturidadeRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

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

    public List<Object[]> getValoresAndNomeByItemId(Long itemDeMaturidadeId) {
        return repository.findValoresAndNomeByItemDeMaturidadeId(itemDeMaturidadeId);
    }

    public List<Map<String, Object>> getValoresAndNomeBytipoMaturidade(TiposMaturidadeEnum tipoMaturidade) {
        return repository.findValoresAndNomeByTipoMaturidade(tipoMaturidade);
    }

    /*public List<ValorDosIndicesDeMaturidadeDto> getValoresByEsteiraIdAndTipoMaturidade(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        List<ValorDosIndicesDeMaturidadeEntity> entities = repository.findByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
        return entities.stream().map(this.mapper::modelToDTO).toList();
    }*/

    public List<Map<String, Object>> getValoresByEsteiraIdAndTipoMaturidade(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        return repository.findByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
    }
}
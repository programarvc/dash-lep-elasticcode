package com.br.agilize.dash.service.dashboardService;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
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
        ValorDosIndicesDeMaturidadeEntity entity = mapper.dtoToModel(payload);
        entity.setId(null); // This will ensure a new entity is created

        ValorDosIndicesDeMaturidadeEntity savedEntity = repository.save(entity);
        return mapper.modelToDTO(savedEntity);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }


   
    public List<Map<String, Object>> getValoresByEsteiraIdAndTipoMaturidade(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        return repository.findByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
    }
    
    public List<ValorDosIndicesDeMaturidadeDto> buscarDadosAtualizados(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        List<ValorDosIndicesDeMaturidadeEntity> entities = repository.findLatestByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
        Map<Long, ValorDosIndicesDeMaturidadeEntity> latestEntities = new HashMap<>();
        for (ValorDosIndicesDeMaturidadeEntity entity : entities) {
            Long itemId = entity.getItemDeMaturidade().getId();
            if (!latestEntities.containsKey(itemId) || entity.getDataHoraValor().isAfter(latestEntities.get(itemId).getDataHoraValor())) {
                latestEntities.put(itemId, entity);
            }
        }
        return latestEntities.values().stream().map(mapper::modelToDTO).collect(Collectors.toList());
    }


    /*public String getLatestItemDeMaturidadeByEsteiraId(Long esteiraId) {
        return repository.findLatestItemDeMaturidadeByEsteiraId(esteiraId).findFirst().orElse(null);
    }*/

    public List<String> getLatestItemDeMaturidadeByEsteiraId(Long esteiraId) {
        LocalDateTime latestDataHora = repository.findLatestDataHoraByEsteiraId(esteiraId).orElse(null);
        if (latestDataHora != null) {
            return repository.findItemDeMaturidadeByEsteiraIdAndDataHora(esteiraId, latestDataHora);
        } else {
            return new ArrayList<>();
        }
    }
    
}
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
    
        //Multiplica os valores por 100
        entity.setValorAtingido(multiplicarPor100(entity.getValorAtingido()));
        entity.setValorEsperado(multiplicarPor100(entity.getValorEsperado()));

        ValorDosIndicesDeMaturidadeEntity savedEntity = repository.save(entity);
        return mapper.modelToDTO(savedEntity);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    // metodo privado para multiplicar valores por 100
    private Double multiplicarPor100(Double valor){
        if(valor != null) {
            return valor * 100;
        }
        return null;
       
    }

   
    public List<Map<String, Object>> getValoresByEsteiraIdAndTipoMaturidade(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        return repository.findByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);
    }
    
    public List<ValorDosIndicesDeMaturidadeDto> buscarDadosAtualizados(Long esteiraId, TiposMaturidadeEnum tipoMaturidade) {
        List<ValorDosIndicesDeMaturidadeEntity> entities = repository.findLatestByEsteiraIdAndTipoMaturidade(esteiraId, tipoMaturidade);     
        return entities.stream().map(mapper::modelToDTO).collect(Collectors.toList());
    }

    public List<Object[]> findLatestByEsteiraIdAndTipoMaturidade(Long maturidadeId) {
        List<Object[]> entities = repository.findLatestByEsteiraIdAndTipoMaturidade(maturidadeId);
        return entities;  
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



     //retorna os dados de acordo com a data de maturidade id em ordem cresente
    public List<Object[]> findByMaturidadeId(Long maturidadeId) {
        List<Object[]> entities = repository.findByMaturidadeId(maturidadeId);
        return entities;
    }

    public List<ValorDosIndicesDeMaturidadeDto> findValorDoIndicesByMaturidadeId(Long maturidadeId) {
        List<ValorDosIndicesDeMaturidadeEntity> entities = repository.findValorDoIndicesByMaturidadeId(maturidadeId);
        return entities.stream().map(mapper::modelToDTO).collect(Collectors.toList());
    }
}
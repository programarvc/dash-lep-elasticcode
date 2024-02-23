package com.br.agilize.dash.service.dashboardService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.MetasColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.MetasOneAOneMapper;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.dto.dashboardDto.MetasOneAOneDto;
import com.br.agilize.dash.model.dto.dashboardDto.MetasColaboradorDto;
import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.MetasOneAOneEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.MetasColaboradorEntity;
import com.br.agilize.dash.repository.dashboardRepository.MetasColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.MetasOneAOneRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

import jakarta.ws.rs.NotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MetasOneAOneService extends ServiceCrudBase<MetasOneAOneDto> {
    @Autowired
    private MetasOneAOneRepository repository;

    @Autowired
    private MetasColaboradorRepository  metasColaboradorRepository;

    @Autowired
    private MetasOneAOneMapper mapper;

    @Autowired 
    private MetasColaboradorMapper metasColaboradorMapper;

    

    @Override
    public MetasOneAOneDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<MetasOneAOneDto> obterTodos() {
        List<MetasOneAOneEntity> MetasOneAOnes = this.repository.findAll();

        return MetasOneAOnes.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public MetasOneAOneDto salvar(MetasOneAOneDto payload) {
        MetasOneAOneEntity MetasOneAOneSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(MetasOneAOneSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public MetasColaboradorDto salvarMetasColaborador(MetasColaboradorDto payload) {
        MetasColaboradorEntity entity = this.metasColaboradorMapper.dtoToModel(payload);
        if (entity == null) {
            throw new IllegalArgumentException("Payload cannot be mapped to entity");
        }
        return metasColaboradorMapper.modelToDTO(this.metasColaboradorRepository.save(entity));
    }

    public MetasColaboradorDto findRecentMetaByColaboradorId(Long colaboradorId) {
        MetasColaboradorEntity entity = metasColaboradorRepository.findByColaboradorIdOrderByDataDesc(colaboradorId)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Meta não encontrada para o colaboradorId: " + colaboradorId));
        return metasColaboradorMapper.modelToDTO(entity);
    }


    public List<MetasColaboradorDto> findAllMetasByColaboradorId(Long colaboradorId) {
        List<MetasColaboradorEntity> entities = metasColaboradorRepository.findByColaboradorIdOrderByDataDesc(colaboradorId)
            .collect(Collectors.toList());
        return entities.stream()
            .map(metasColaboradorMapper::modelToDTO)
            .collect(Collectors.toList());
    }
    
}
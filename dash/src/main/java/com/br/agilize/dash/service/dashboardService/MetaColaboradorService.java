package com.br.agilize.dash.service.dashboardService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.MetaColaboradorMapper;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.dto.dashboardDto.MetaColaboradorDto;
import com.br.agilize.dash.model.entity.CompetenciaColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.CapacidadesRecomendadasEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.MetaColaboradorEntity;
import com.br.agilize.dash.repository.CompetenciaColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.MetaColaboradorRepository;
import com.br.agilize.dash.service.ServiceCrudBase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import jakarta.ws.rs.NotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MetaColaboradorService extends ServiceCrudBase<MetaColaboradorDto> {

    @Autowired
    private MetaColaboradorRepository  repository;

    @Autowired 
    private MetaColaboradorMapper mapper;

    @Autowired
    private CompetenciaColaboradorRepository competenciaColaboradorRepository;

    @Override
    public MetaColaboradorDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<MetaColaboradorDto> obterTodos() {
        List<MetaColaboradorEntity> MetaColaboradors = this.repository.findAll();

        return MetaColaboradors.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public MetaColaboradorDto salvar(MetaColaboradorDto payload) {
        MetaColaboradorEntity MetaColaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(MetaColaboradorSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

  
}
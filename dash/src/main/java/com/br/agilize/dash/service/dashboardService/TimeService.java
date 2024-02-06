package com.br.agilize.dash.service.dashboardService;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.EsteiraDeDesenvolvimentoMapper;
import com.br.agilize.dash.mapper.dashboardMapper.TimeColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.TimeMapper;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;
import com.br.agilize.dash.repository.ColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.TimeColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.TimeRepository;
import com.br.agilize.dash.service.ColaboradorService;
import com.br.agilize.dash.service.ServiceCrudBase;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TimeService extends ServiceCrudBase<TimeDto> {
    @Autowired
    private TimeRepository repository;

    @Autowired
    private TimeMapper mapper;

    @Autowired
    private TimeColaboradorMapper timeColaboradorMapper;

    @Autowired
    private EsteiraDeDesenvolvimentoMapper esteiraMapper;

    @Autowired
    private ColaboradorMapper colaboradorMapper;


    @Autowired 
    private TimeColaboradorRepository timeColaboradorRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepositor;

    @Autowired
    private EsteiraDeDesenvolvimentoService esteiraService;

    @Autowired
    private ColaboradorService colaboradorService;



    @Override
    public TimeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<TimeDto> obterTodos() {
        List<TimeEntity> Times = this.repository.findAll();

        return Times.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public TimeDto salvar(TimeDto payload) {
        TimeEntity TimeSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(TimeSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

   

    public TimeColaboradorDto salvarTimeColaborador(TimeColaboradorDto payload) {
        TimeColaboradorEntity entity = this.timeColaboradorMapper.dtoToModel(payload);
        if (entity == null) {
            throw new IllegalArgumentException("Payload cannot be mapped to entity");
        }
        return timeColaboradorMapper.modelToDTO(this.timeColaboradorRepository.save(entity));
    }

    /*public List<TimeColaboradorDto> findTimeAndColaboradorByEsteiraId(Long esteiraId) {
        List<TimeColaboradorEntity> timeAndColaboradores = this.timeColaboradorRepository.findTimeAndColaboradorByEsteiraId(esteiraId);
        return timeAndColaboradores.stream().map(this.timeColaboradorMapper::modelToDTO).collect(Collectors.toList());
    }
    
    public List<ColaboradorDto> findColaboradoresByEsteiraIdAndNomeTime(Long esteiraId, String nomeTime) {
        List<ColaboradorEntity> colaboradores = this.timeColaboradorRepository.findColaboradoresByEsteiraIdAndNomeTime(esteiraId, nomeTime);
        return colaboradores.stream().map(this.ColaboradorMapper::modelToDTO).collect(Collectors.toList());
    }
    
    public List<TimeColaboradorDto> findTimesByEsteiraIdAndColaboradorId(Long esteiraId, Long colaboradorId) {
        List<TimeColaboradorEntity> times = this.repository.findTimesByEsteiraIdAndColaboradorId(esteiraId, colaboradorId);
        return times.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
    
        }*/

public List<TimeColaboradorDto> getTimeAndColaboradorByEsteiraId(Long esteiraId) {
    List<TimeColaboradorEntity> timeAndColaboradores = this.timeColaboradorRepository.findTimeAndColaboradorByEsteiraId(esteiraId);
    return timeAndColaboradores.stream().map(this.timeColaboradorMapper::modelToDTO).collect(Collectors.toList());
}

public List<ColaboradorDto> getColaboradoresByEsteiraId(Long esteiraId) {
    List<ColaboradorEntity> colaboradores = this.timeColaboradorRepository.findColaboradoresByEsteiraId(esteiraId);
    return colaboradores.stream().map(this.colaboradorMapper::modelToDTO).collect(Collectors.toList());
}

public List<TimeDto> getTimesByEsteiraId(Long esteiraId) {
    List<TimeEntity> times = this.timeColaboradorRepository.findTimesByEsteiraId(esteiraId);
    return times.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
}

public List<TimeDto> getTimesByColaboradorId(Long colaboradorId) {
    List<TimeEntity> timesAndEsteira = this.timeColaboradorRepository.findTimesAndEsteiraByColaboradorId(colaboradorId);
    return timesAndEsteira.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
}

public List<ColaboradorDto> findColaboradoresByTimeId(Long timeId) {
    List<ColaboradorEntity> colaboradores = this.timeColaboradorRepository.findColaboradoresByTimeId(timeId);
    return colaboradores.stream().map(this.colaboradorMapper::modelToDTO).collect(Collectors.toList());
    
}
   
}
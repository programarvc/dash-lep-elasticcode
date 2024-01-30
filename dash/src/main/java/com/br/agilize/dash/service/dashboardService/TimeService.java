package com.br.agilize.dash.service.dashboardService;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.TimeEsteiraMapper;
import com.br.agilize.dash.mapper.dashboardMapper.TimeMapper;
import com.br.agilize.dash.model.dto.dashboardDto.TimeDto;
import com.br.agilize.dash.model.dto.dashboardDto.TimeEsteiraDto;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeEsteiraEntity;
import com.br.agilize.dash.repository.dashboardRepository.TimeEsteiraRepository;
import com.br.agilize.dash.repository.dashboardRepository.TimeRepository;
import com.br.agilize.dash.service.ServiceCrudBase;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TimeService extends ServiceCrudBase<TimeDto> {
    @Autowired
    private TimeRepository repository;

    @Autowired
    private TimeMapper mapper;

    @Autowired
    private TimeEsteiraMapper timeEsteiraMapper;


    @Autowired 
    private TimeEsteiraRepository timeEsteiraRepository;


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
        if (repository.existsByNome(payload.getNome())) {
            throw new DataIntegrityViolationException("Time com name " + payload.getNome() + " já existe");
        }
        TimeEntity TimeSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(TimeSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

   

    public TimeEsteiraDto salvarTimeEsteira(TimeEsteiraDto payload) {
        TimeEsteiraEntity entity = this.timeEsteiraMapper.dtoToModel(payload);
        if (entity == null) {
            throw new IllegalArgumentException("Payload cannot be mapped to entity");
        }
        return timeEsteiraMapper.modelToDTO(this.timeEsteiraRepository.save(entity));
    }

    public List<Map<String, String>> buscarNomesDosTimesEDosColaboradoresPorEsteiraId(Long esteiraId) {
        return this.timeEsteiraRepository.findTimeAndColaboradorNamesByEsteiraId(esteiraId);
    }
   
}
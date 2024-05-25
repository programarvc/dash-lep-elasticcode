package com.br.agilize.dash.service.dashboardService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.CompetenciaMapper;
import com.br.agilize.dash.mapper.dashboardMapper.MetaColaboradorMapper;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.CompetenciaDto;
import com.br.agilize.dash.model.dto.dashboardDto.CapacidadesRecomendadasDto;
import com.br.agilize.dash.model.dto.dashboardDto.MetaColaboradorDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaEntity;
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
    private ColaboradorMapper colaboradorMapper;

    @Autowired
    private CompetenciaMapper competenciaMapper;

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
        ColaboradorDto colaboradorDto = payload.getColaborador();
        CompetenciaDto competenciaDto = payload.getCompetencia();
    
        if (colaboradorDto == null || competenciaDto == null) {
            throw new RuntimeException("Colaborador ou Competencia não podem ser nulos");
        }
    
        ColaboradorEntity colaboradorEntity = colaboradorMapper.dtoToModel(colaboradorDto);
        CompetenciaEntity competenciaEntity = competenciaMapper.dtoToModel(competenciaDto);
    
        if (!competenciaColaboradorRepository.existsByColaboradorAndCompetencia(colaboradorEntity, competenciaEntity)) {
            throw new RuntimeException("Essa Competencia não existe para o colaborador informado");
        }
    
        MetaColaboradorEntity MetaColaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(MetaColaboradorSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    //Metodo para buscar as 3 competencias com as ultimas notas de uma competencia de um colaborador em uma data
    public List<Map<String, Object>> getTop3CompetenciasByColaborador(Long colaboradorId) {
        return repository.findTop3CompetenciasByColaborador(colaboradorId);
    }

    public Map<LocalDate, List<Map<String, Object>>> getCompetenciasWithAtLeast3Competencias(Long colaboradorId) {
        List<Object[]> datasObjects = repository.findDatasWithAtLeast3Competencias(colaboradorId);
        List<LocalDate> datas = datasObjects.stream()
                .map(dateObj -> LocalDate.parse(dateObj[0].toString()))
                .collect(Collectors.toList());

        Map<LocalDate, List<Map<String, Object>>> competenciasPorData = new HashMap<>();

        for (LocalDate data : datas) {
            List<Map<String, Object>> competencias = repository.findTop3CompetenciasByColaboradorAndData(colaboradorId, data);
            competenciasPorData.put(data, competencias);
        }

        return competenciasPorData;
    }

    public List<LocalDate> getDatasWithAtLeast3Competencias(Long colaboradorId) {
        List<Object[]> datasObjects = repository.findDatasWithAtLeast3Competencias(colaboradorId);
        return datasObjects.stream()
                .map(dateObj -> LocalDate.parse(dateObj[0].toString()))
                .collect(Collectors.toList());
    }

    public Map<String, List<Map<String, Object>>> getTop3CompetenciasByColaboradorAndData(Long colaboradorId, LocalDate data) {
        List<Map<String, Object>> results = repository.findTop3CompetenciasByColaboradorAndData(colaboradorId, data);
        Map<String, List<Map<String, Object>>> groupedResults = new HashMap<>();
        for (Map<String, Object> result : results) {
            Map<String, Object> mutableResult = new HashMap<>(result);
            if (mutableResult.containsKey("data") && mutableResult.get("data") != null) {
                java.sql.Date sqlDate = (java.sql.Date) mutableResult.get("data");
                long timestamp = sqlDate.getTime();
                mutableResult.put("data", timestamp);
                String date = sqlDate.toString();
                if (!groupedResults.containsKey(date)) {
                    groupedResults.put(date, new ArrayList<>());
                }
                groupedResults.get(date).add(mutableResult);
            }
        }
        return groupedResults;
    }
  
}
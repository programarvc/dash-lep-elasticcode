package com.br.agilize.dash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.br.agilize.dash.model.entity.AcoesColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaColaboradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.CompetenciaColaboradorMapper;
import com.br.agilize.dash.mapper.CompetenciaMapper;
import com.br.agilize.dash.model.dto.CompetenciaColaboradorDto;
import com.br.agilize.dash.model.dto.CompetenciaDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.CompetenciaEntity;
import com.br.agilize.dash.repository.CompetenciaColaboradorRepository;
import com.br.agilize.dash.repository.CompetenciaRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CompetenciaService extends ServiceCrudBase<CompetenciaDto> {
    @Autowired
    private CompetenciaRepository repository;
    @Autowired
    private CompetenciaMapper mapper;

    @Autowired
    private CompetenciaColaboradorRepository competenciaColaboradorRepository;

    @Autowired
    private CompetenciaColaboradorMapper competenciaColaboradorMapper;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Autowired
    private ColaboradorService colaboradorService;

    @Override
    public CompetenciaDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<CompetenciaDto> obterTodos() {
        List<CompetenciaEntity> salvos = this.repository.findAll();

        return salvos.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public CompetenciaDto salvar(CompetenciaDto payload) {
        CompetenciaEntity salvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(salvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public CompetenciaColaboradorDto salvarCompetenciaColaborador(CompetenciaColaboradorDto payload) {
        return competenciaColaboradorMapper.modelToDTO(
                this.competenciaColaboradorRepository.save(
                        this.competenciaColaboradorMapper.dtoToModel(payload)) );
    }


    public List<CompetenciaColaboradorDto> obterCompetenciaColaborador(Long colaboradorId) {
        ColaboradorEntity colaborador = colaboradorMapper.dtoToModel(colaboradorService.obterPorId(colaboradorId));
        return this.competenciaColaboradorRepository
                .findByColaborador(colaborador).stream()
                .map(this.competenciaColaboradorMapper::modelToDTO).collect(Collectors.toList());

    }

    public List<Map<String, Long>> obterTodasCompetenciasColaboradores() {
        List<CompetenciaColaboradorEntity> todasAssociacoes = competenciaColaboradorRepository.findAll();

        return todasAssociacoes.stream()
                .map(competenciaAssociacao -> {
                    Map<String, Long> ids = new HashMap<>();
                    ids.put("colaborador_id", competenciaAssociacao.getColaborador().getId());
                    ids.put("competencia_id", competenciaAssociacao.getCompetencia().getId());
                    ids.put("id", competenciaAssociacao.getId());
                    return ids;
                })
                .collect(Collectors.toList());
    }
}

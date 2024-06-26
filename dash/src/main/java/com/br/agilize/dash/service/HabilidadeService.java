package com.br.agilize.dash.service;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;

import com.br.agilize.dash.mapper.HabilidadeMapper;

import com.br.agilize.dash.mapper.HabilidadeColaboradorMapper;
import com.br.agilize.dash.model.dto.HabilidadeColaboradorDto;
import com.br.agilize.dash.model.dto.HabilidadeDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;

import com.br.agilize.dash.model.entity.HabilidadeColaboradorEntity;
import com.br.agilize.dash.model.entity.HabilidadeEntity;
import com.br.agilize.dash.repository.HabilidadeColaboradorRepository;
import com.br.agilize.dash.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class HabilidadeService extends ServiceCrudBase<HabilidadeDto> {
    @Autowired
    private HabilidadeRepository repository;
    @Autowired
    private HabilidadeMapper mapper;
    @Autowired
    private HabilidadeColaboradorMapper habilidadeColaboradorMapper;
    @Autowired
    private ColaboradorService colaboradorService;
    @Autowired
    private ColaboradorMapper colaboradorMapper;
    @Autowired
    private HabilidadeColaboradorRepository habilidadeColaboradorRepository;

    @Override
    public HabilidadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<HabilidadeDto> obterTodos() {
        List<HabilidadeEntity> salvos = this.repository.findAll();

        return salvos.stream().map(this.mapper::modelToDTO).toList();
    }

    // No seu serviço HabilidadeService.java
    @Override
    public HabilidadeDto salvar(HabilidadeDto payload) {
        Optional<HabilidadeEntity> existingHabilidade = this.repository.findByNome(payload.getNome());
        if (existingHabilidade.isPresent()) {
            throw new RuntimeException("Habilidade ja existe");
        }
        HabilidadeEntity habilidadeSalva = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(habilidadeSalva);
    }

    
    
    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public List<HabilidadeColaboradorDto> obterHabilidadesColaborador(Long colaboradorId) {
        ColaboradorEntity colaborador = colaboradorMapper.dtoToModel(colaboradorService.obterPorId(colaboradorId));
        return this.habilidadeColaboradorRepository
                .findByColaborador(colaborador).stream()
                .map(this.habilidadeColaboradorMapper::modelToDTO).collect(Collectors.toList());

    }


    public HabilidadeColaboradorDto salvarHabilidadeColaborador(HabilidadeColaboradorDto payload) {
        return habilidadeColaboradorMapper.modelToDTO(
                this.habilidadeColaboradorRepository.save(
                        this.habilidadeColaboradorMapper.dtoToModel(payload)) );
    }

    public void apagarHabilidadeColaborador(Long colaboradorId, Long habilidadeId) {
        HabilidadeColaboradorEntity habilidadeColaborador = this.habilidadeColaboradorRepository.findByColaboradorIdAndHabilidadeId(colaboradorId, habilidadeId);
        this.habilidadeColaboradorRepository.delete(habilidadeColaborador);
    }

    public List<HabilidadeColaboradorDto> findByHabilidade(Long id) {
        HabilidadeEntity habilidade = this.mapper.dtoToModel(this.obterPorId(id));

        return this.habilidadeColaboradorRepository.findByHabilidade(habilidade).stream()
                .map(this.habilidadeColaboradorMapper::modelToDTO).collect(Collectors.toList());
    }

    public List<Map<String, Long>> obterTodasHabilidadesColaborador() {
        List<HabilidadeColaboradorEntity> todasAssociacoes = habilidadeColaboradorRepository.findAll();

        return todasAssociacoes.stream()
                .map(habilidadeAssociacao -> {
                    Map<String, Long> ids = new HashMap<>();
                    ids.put("colaborador_id", habilidadeAssociacao.getColaborador().getId());
                    ids.put("habilidade_id", habilidadeAssociacao.getHabilidade().getId());
                    ids.put("id", habilidadeAssociacao.getId());
                    return ids;
                })
                .collect(Collectors.toList());
    }

    // Novo método para obter as duas primeiras habilidades de um colaborador
    public List<HabilidadeColaboradorDto> obterPrimeirasHabilidadesColaborador(Long colaboradorId) {
        List<HabilidadeEntity> habilidades = habilidadeColaboradorRepository.findTop2HabilidadesByColaboradorId(colaboradorId, PageRequest.of(0, 2));
        return habilidades.stream()
                .map(habilidade -> {
                    HabilidadeColaboradorDto dto = new HabilidadeColaboradorDto();
                    dto.setHabilidade(mapper.modelToDTO(habilidade));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    //Retornar Stacks para devs
    public List<HabilidadeDto> obterDevStacks() {
        List<HabilidadeEntity> devStacks = this.repository.getDevStacks();
        return devStacks.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
    }

    //Retornar Tecnologias para devs
    public List<HabilidadeDto> obterDevTechnologies() {
        List<HabilidadeEntity> devTechnologies = this.repository.getDevTechnologies();
        return devTechnologies.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
    }
}
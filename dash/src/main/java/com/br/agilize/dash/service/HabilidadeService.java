package com.br.agilize.dash.service;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;

import com.br.agilize.dash.mapper.HabilidadeMapper;

import com.br.agilize.dash.mapper.HabilidadeColaboradorMapper;
import com.br.agilize.dash.model.dto.HabilidadeColaboradorDto;
import com.br.agilize.dash.model.dto.HabilidadeDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;

import com.br.agilize.dash.model.entity.HabilidadeEntity;
import com.br.agilize.dash.repository.HabilidadeColaboradorRepository;
import com.br.agilize.dash.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public HabilidadeDto salvar(HabilidadeDto payload) {
        HabilidadeEntity colaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(colaboradorSalvo);
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

    public List<HabilidadeColaboradorDto> findByHabilidade(Long id) {
        HabilidadeEntity habilidade = this.mapper.dtoToModel(this.obterPorId(id));

        return this.habilidadeColaboradorRepository.findByHabilidade(habilidade).stream()
                .map(this.habilidadeColaboradorMapper::modelToDTO).collect(Collectors.toList());
    }
}

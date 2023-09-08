package com.br.agilize.dash.service;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.EmpresaColaboradorMapper;
import com.br.agilize.dash.mapper.EmpresaMapper;
import com.br.agilize.dash.model.dto.EmpresaColaboradorDto;

import com.br.agilize.dash.model.dto.EmpresaDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.EmpresaEntity;

import com.br.agilize.dash.repository.EmpresaColaboradorRepository;
import com.br.agilize.dash.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmpresaService extends ServiceCrudBase<EmpresaDto> {
    @Autowired

    private EmpresaRepository repository;
    @Autowired
    private EmpresaMapper mapper;

    @Autowired
    private EmpresaColaboradorRepository empresaColaboradorRepository;
    @Autowired
    private EmpresaColaboradorMapper empresaColaboradorMapper;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Autowired
    private ColaboradorService colaboradorService;

    @Override
    public EmpresaDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<EmpresaDto> obterTodos() {
        List<EmpresaEntity> salvos = this.repository.findAll();

        return salvos.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public EmpresaDto salvar(EmpresaDto payload) {
        EmpresaEntity colaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(colaboradorSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

   

     public EmpresaColaboradorDto salvarEmpresasColaborador(EmpresaColaboradorDto payload) {
        return empresaColaboradorMapper.modelToDTO(
                this.empresaColaboradorRepository.save(
                        this.empresaColaboradorMapper.dtoToModel(payload)) );
    }


    public List<EmpresaColaboradorDto> obterEmpresasColaborador(Long colaboradorId) {
        ColaboradorEntity colaborador = colaboradorMapper.dtoToModel(colaboradorService.obterPorId(colaboradorId));
        return this.empresaColaboradorRepository
                .findByColaborador(colaborador).stream()
                .map(this.empresaColaboradorMapper::modelToDTO).collect(Collectors.toList());

    }

}

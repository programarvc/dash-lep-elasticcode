package com.br.agilize.dash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.br.agilize.dash.model.entity.EmpresaColaboradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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


    public void apagarEmpresaColaborador(Long colaboradorId, Long empresaId) {
        EmpresaColaboradorEntity empresaColaboradorEntity = this.empresaColaboradorRepository
                .findByColaboradorIdAndEmpresaId(colaboradorId, empresaId);
        this.empresaColaboradorRepository.delete(empresaColaboradorEntity);
    }



    public List<EmpresaColaboradorDto> obterEmpresasColaborador(Long colaboradorId) {
        ColaboradorEntity colaborador = colaboradorMapper.dtoToModel(colaboradorService.obterPorId(colaboradorId));
        return this.empresaColaboradorRepository
                .findByColaborador(colaborador).stream()
                .map(this.empresaColaboradorMapper::modelToDTO).collect(Collectors.toList());

    }

    public List<EmpresaColaboradorDto> obterEmpresasColaboradorAll (Long id) {
        List<EmpresaColaboradorEntity> empresaColaboradorEntities = this.empresaColaboradorRepository.findByEmpresaId(id);
        return empresaColaboradorEntities.stream()
                .map(this.empresaColaboradorMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    public List<Map<String, Long>> obterTodasEmpresasColaboradores() {
        List<EmpresaColaboradorEntity> todasAssociacoes = empresaColaboradorRepository.findAll();

        return todasAssociacoes.stream()
                .map(associacao -> {
                    Map<String, Long> ids = new HashMap<>();
                    ids.put("colaborador_id", associacao.getColaborador().getId());
                    ids.put("empresa_id", associacao.getEmpresa().getId());
                    ids.put("id", associacao.getId());
                    return ids;
                })
                .collect(Collectors.toList());
    }

    public List<EmpresaColaboradorDto> findByEmpresa(Long id) {
        EmpresaEntity empresa = this.mapper.dtoToModel(this.obterPorId(id));

        return this.empresaColaboradorRepository.findByEmpresa(empresa).stream()
                .map(this.empresaColaboradorMapper::modelToDTO).collect(Collectors.toList());
    }
}
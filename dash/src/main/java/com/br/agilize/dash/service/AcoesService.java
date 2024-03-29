package com.br.agilize.dash.service;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.AcoesColaboradorMapper;
import com.br.agilize.dash.mapper.AcoesMapper;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.model.dto.AcoesColaboradorDto;
import com.br.agilize.dash.model.dto.AcoesDto;
import com.br.agilize.dash.model.entity.AcoesColaboradorEntity;
import com.br.agilize.dash.model.entity.AcoesEntity;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.repository.AcoesColaboradorRepository;
import com.br.agilize.dash.repository.AcoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AcoesService extends ServiceCrudBase<AcoesDto> {
    @Autowired
    private AcoesRepository repository;
    @Autowired
    private AcoesMapper mapper;

    @Autowired
    private AcoesColaboradorRepository acoesColaboradorRepository;
    @Autowired
    private AcoesColaboradorMapper acoesColaboradorMapper;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Autowired
    private ColaboradorService colaboradorService;

    @Override
    public AcoesDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<AcoesDto> obterTodos() {
        List<AcoesEntity> salvos = this.repository.findAll();

        return salvos.stream().map(this.mapper::modelToDTO).toList();
    }

    
    @Override
    public AcoesDto salvar(AcoesDto payload) {
        Optional<AcoesEntity> existingAcao = this.repository.findByNome(payload.getNome());
        if (existingAcao.isPresent()) {
            throw new RuntimeException("Ação ja existe");
        }
        AcoesEntity acaoSalva = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(acaoSalva);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public AcoesColaboradorDto salvarAcaoColaborador(AcoesColaboradorDto payload) {
        return acoesColaboradorMapper.modelToDTO(
                this.acoesColaboradorRepository.save(
                        this.acoesColaboradorMapper.dtoToModel(payload)) );
    }

    public void apagarAcaoColaborador(Long colaboradorId, Long id) {
        AcoesColaboradorEntity acoesColaboradorEntity = this.acoesColaboradorRepository
                .findByColaboradorIdAndAcaoId(colaboradorId, id);
        this.acoesColaboradorRepository.delete(acoesColaboradorEntity);
    }


    public List<AcoesColaboradorDto> obterAcoesColaborador(Long colaboradorId) {
        ColaboradorEntity colaborador = colaboradorMapper.dtoToModel(colaboradorService.obterPorId(colaboradorId));
        return this.acoesColaboradorRepository
                .findByColaborador(colaborador).stream()
                .map(this.acoesColaboradorMapper::modelToDTO).collect(Collectors.toList());

    }

    public List<Map<String, Long>> obterTodasAcoesColaborador() {
        List<AcoesColaboradorEntity> todasAssociacoes = acoesColaboradorRepository.findAll();

        return todasAssociacoes.stream()
                .map(acaoAssociacao -> {
                    Map<String, Long> ids = new HashMap<>();
                    ids.put("colaborador_id", acaoAssociacao.getColaborador().getId());
                    ids.put("acao_id", acaoAssociacao.getAcao().getId());
                    ids.put("id", acaoAssociacao.getId());
                    return ids;
                })
                .collect(Collectors.toList());
    }
}

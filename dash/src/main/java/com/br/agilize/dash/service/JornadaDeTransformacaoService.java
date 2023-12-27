package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.JornadaDeTransformacaoMapper;
import com.br.agilize.dash.model.dto.JornadaDeTransformacaoDto;
import com.br.agilize.dash.model.entity.JornadaDeTransformacaoEntity;
import com.br.agilize.dash.repository.JornadaDeTransformacaoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class JornadaDeTransformacaoService extends ServiceCrudBase<JornadaDeTransformacaoDto> {
    @Autowired
    private JornadaDeTransformacaoRepository repository;

    @Autowired
    private JornadaDeTransformacaoMapper mapper;

    @Override
    public JornadaDeTransformacaoDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<JornadaDeTransformacaoDto> obterTodos() {
        List<JornadaDeTransformacaoEntity> JornadaDeTransformacaos = this.repository.findAll();

        return JornadaDeTransformacaos.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public JornadaDeTransformacaoDto salvar(JornadaDeTransformacaoDto payload) {
        JornadaDeTransformacaoEntity JornadaDeTransformacaoSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(JornadaDeTransformacaoSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.EsteiraDeDesenvolvimentoMapper;
import com.br.agilize.dash.model.dto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.model.entity.EsteiraDeDesenvolvimentoEntity;
import com.br.agilize.dash.repository.EsteiraDeDesenvolvimentoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EsteiraDeDesenvolvimentoService extends ServiceCrudBase<EsteiraDeDesenvolvimentoDto> {
    @Autowired
    private EsteiraDeDesenvolvimentoRepository repository;

    @Autowired
    private EsteiraDeDesenvolvimentoMapper mapper;

    @Override
    public EsteiraDeDesenvolvimentoDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<EsteiraDeDesenvolvimentoDto> obterTodos() {
        List<EsteiraDeDesenvolvimentoEntity> EsteiraDeDesenvolvimentos = this.repository.findAll();

        return EsteiraDeDesenvolvimentos.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public EsteiraDeDesenvolvimentoDto salvar(EsteiraDeDesenvolvimentoDto payload) {
        EsteiraDeDesenvolvimentoEntity EsteiraDeDesenvolvimentoSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(EsteiraDeDesenvolvimentoSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
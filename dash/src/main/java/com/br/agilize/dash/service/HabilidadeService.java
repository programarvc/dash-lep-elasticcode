package com.br.agilize.dash.service;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.HabilidadeMapper;
import com.br.agilize.dash.model.dto.HabilitadeDto;
import com.br.agilize.dash.model.entity.HabilitadeEntity;
import com.br.agilize.dash.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class HabilidadeService extends ServiceCrudBase<HabilitadeDto> {
    @Autowired
    private HabilidadeRepository repository;
    @Autowired
    private HabilidadeMapper mapper;

    @Override
    public HabilitadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<HabilitadeDto> obterTodos() {
        List<HabilitadeEntity> salvos = this.repository.findAll();

        return salvos.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public HabilitadeDto salvar(HabilitadeDto payload) {
        HabilitadeEntity colaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(colaboradorSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}

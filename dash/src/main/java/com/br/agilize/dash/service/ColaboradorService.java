package com.br.agilize.dash.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.repository.ColaboradorRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ColaboradorService extends ServiceCrudBase<ColaboradorDto> {
    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorMapper mapper;

    @Override
    public ColaboradorDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<ColaboradorDto> obterTodos() {
        List<ColaboradorEntity> colaboradores = this.repository.findAll();

        return colaboradores.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public ColaboradorDto salvar(ColaboradorDto payload) {
        ColaboradorEntity colaboradorSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(colaboradorSalvo);
    }


    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public List<ColaboradorDto> getColaboradoresByEsteiraId(Long esteiraId) {
        List <ColaboradorEntity> colaboradores = this.repository.findColaboradoresByEsteiraId(esteiraId);
        return colaboradores.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
    }

   /* public List<ColaboradorDto> getColaboradoresByEsteiraId(Long esteiraId) {
        List<ColaboradorEntity> colaboradores = this.repository.getColaboradoresByEsteiraId(esteiraId);
        return colaboradores.stream().map(this.mapper::modelToDTO).collect(Collectors.toList());
    }*/

    

    /* 
    public ColaboradorDto criarNovoColaborador(ColaboradorDto novoColaborador) {
        ColaboradorEntity entity = this.mapper.dtoToModel(novoColaborador);
        if (entity == null) {
            throw new IllegalArgumentException("Payload cannot be mapped to entity");
        }
        ColaboradorEntity colaboradorSalvo = this.repository.save(entity);
        return this.mapper.modelToDTO(colaboradorSalvo);
    }*/
}
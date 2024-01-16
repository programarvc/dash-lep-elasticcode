package com.br.agilize.dash.service.dashboardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.EsteiraDeDesenvolvimentoMapper;
import com.br.agilize.dash.model.dto.dashboardDto.EsteiraDeDesenvolvimentoDto;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;
import com.br.agilize.dash.repository.dashboardRepository.EsteiraDeDesenvolvimentoRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

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
        EsteiraDeDesenvolvimentoEntity entity = this.mapper.dtoToModel(payload);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        EsteiraDeDesenvolvimentoEntity EsteiraDeDesenvolvimentoSalvo = this.repository.save(entity);
        return this.mapper.modelToDTO(EsteiraDeDesenvolvimentoSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
}
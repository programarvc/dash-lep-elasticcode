package com.br.agilize.dash.service.dashboardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.ItemDeMaturidadeMapper;
import com.br.agilize.dash.model.dto.dashboardDto.ItemDeMaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.ItemDeMaturidadeEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.ValorDosIndicesDeMaturidadeEntity;
import com.br.agilize.dash.model.enums.TiposMaturidadeEnum;
import com.br.agilize.dash.repository.dashboardRepository.ItemDeMaturidadeRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemDeMaturidadeService extends ServiceCrudBase<ItemDeMaturidadeDto> {
    @Autowired
    private ItemDeMaturidadeRepository repository;

    @Autowired
    private ItemDeMaturidadeMapper mapper;

    @Override
    public ItemDeMaturidadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<ItemDeMaturidadeDto> obterTodos() {
        List<ItemDeMaturidadeEntity> ItemDeMaturidades = this.repository.findAll();

        return ItemDeMaturidades.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public ItemDeMaturidadeDto salvar(ItemDeMaturidadeDto payload) {
        ItemDeMaturidadeEntity ItemDeMaturidadeSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(ItemDeMaturidadeSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public List<ValorDosIndicesDeMaturidadeEntity> getByTipoMaturidade(TiposMaturidadeEnum tipoMaturidade) {
        return null;
    }
}
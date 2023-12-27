package com.br.agilize.dash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ItemDeMaturidadeMapper;
import com.br.agilize.dash.model.dto.ItemDeMaturidadeDto;
import com.br.agilize.dash.model.entity.ItemDeMaturidadeEntity;
import com.br.agilize.dash.repository.ItemDeMaturidadeRepository;

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
}
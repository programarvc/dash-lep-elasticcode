
package com.br.agilize.dash.repository;

import com.br.agilize.dash.model.entity.ItemDeMaturidadeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDeMaturidadeRepository extends JpaRepository<ItemDeMaturidadeEntity, Long> {
    
}


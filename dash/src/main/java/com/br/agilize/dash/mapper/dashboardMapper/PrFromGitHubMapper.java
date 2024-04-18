package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.PrFromGitHubDto;
import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;

import org.mapstruct.Mapper;

@Mapper
public interface PrFromGitHubMapper extends  MapperBase<PrFromGitHubEntity, PrFromGitHubDto> {
    @Override
    PrFromGitHubDto modelToDTO(PrFromGitHubEntity entity);

    @Override
    PrFromGitHubEntity dtoToModel(PrFromGitHubDto dto);
}
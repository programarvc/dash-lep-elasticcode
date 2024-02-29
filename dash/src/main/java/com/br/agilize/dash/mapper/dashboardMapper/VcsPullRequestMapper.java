package com.br.agilize.dash.mapper.dashboardMapper;

import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.VcsPullRequestDto;
import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VcsPullRequestMapper extends  MapperBase<VcsPullRequestEntity, VcsPullRequestDto> {
    @Override
    VcsPullRequestDto modelToDTO(VcsPullRequestEntity entity);

    @Override
    VcsPullRequestEntity dtoToModel(VcsPullRequestDto dto);
}
package com.br.agilize.dash.mapper.dashboardMapper;
import com.br.agilize.dash.mapper.MapperBase;
import com.br.agilize.dash.model.dto.dashboardDto.UserDto;
import com.br.agilize.dash.model.entity.dashboardEntity.UserEntity;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends  MapperBase<UserEntity, UserDto> {
    @Override
    UserDto modelToDTO(UserEntity entity);

    @Override
    UserEntity dtoToModel(UserDto dto);
}
package com.br.agilize.dash.mapper;
import com.br.agilize.dash.model.dto.EmpresaDto;
import com.br.agilize.dash.model.entity.EmpresaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EmpresaMapper extends  MapperBase<EmpresaEntity, EmpresaDto> {
    @Override
    EmpresaDto modelToDTO(EmpresaEntity entity);

    @Override
    EmpresaEntity dtoToModel(EmpresaDto dto);
}
package com.br.agilize.dash.mapper;
import org.mapstruct.Mapper;
import com.br.agilize.dash.model.dto.EmpresaColaboradorDto;
import com.br.agilize.dash.model.entity.EmpresaColaboradorEntity;

@Mapper
public interface EmpresaColaboradorMapper extends  MapperBase<EmpresaColaboradorEntity , EmpresaColaboradorDto> {
    @Override
    EmpresaColaboradorDto modelToDTO(EmpresaColaboradorEntity entity);

    @Override
        EmpresaColaboradorEntity dtoToModel(EmpresaColaboradorDto dto);
}

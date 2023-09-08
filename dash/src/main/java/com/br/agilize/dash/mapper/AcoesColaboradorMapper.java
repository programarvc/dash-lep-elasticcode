package com.br.agilize.dash.mapper;
import com.br.agilize.dash.model.dto.AcoesColaboradorDto;
import com.br.agilize.dash.model.entity.AcoesColaboradorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AcoesColaboradorMapper extends  MapperBase<AcoesColaboradorEntity, AcoesColaboradorDto> {
    @Override
    AcoesColaboradorDto modelToDTO(AcoesColaboradorEntity entity);

    @Override
    AcoesColaboradorEntity dtoToModel(AcoesColaboradorDto dto);
}

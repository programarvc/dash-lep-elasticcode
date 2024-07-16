package com.br.agilize.dash.mapper.dashboardMapper;

public interface HasuraMapperBase<E, D> {
        D modelToDTO(E entity);
        E dtoToModel(D dto);
}

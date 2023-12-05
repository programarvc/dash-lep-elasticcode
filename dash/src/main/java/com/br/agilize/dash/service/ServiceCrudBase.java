package com.br.agilize.dash.service;

import java.util.List;

public abstract class ServiceCrudBase<T> {

    public abstract T obterPorId( Long id) ;

    public abstract List<T> obterTodos();

    public abstract T salvar( T payload);

    public abstract void excluirPorId( Long id);
}

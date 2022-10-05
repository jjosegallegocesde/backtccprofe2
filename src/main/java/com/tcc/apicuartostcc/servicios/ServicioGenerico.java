package com.tcc.apicuartostcc.servicios;

import java.util.List;

public interface ServicioGenerico<E> {

    public List<E> buscarTodos() throws Exception;
    public E buscarPorId(Integer id) throws  Exception;
    public E registrar(E tabla) throws Exception;
    public E actualizar(Integer id, E tabla) throws Exception;
    public Boolean borrar(Integer id) throws Exception;

}

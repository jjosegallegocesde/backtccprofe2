package com.tcc.apicuartostcc.servicios.implementaciones;


import com.tcc.apicuartostcc.entidades.Mercancia;
import com.tcc.apicuartostcc.entidades.Zona;
import com.tcc.apicuartostcc.repositorios.Mercanciarepositorio;
import com.tcc.apicuartostcc.repositorios.Zonarepositorio;
import com.tcc.apicuartostcc.servicios.ServicioGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercanciaServicioImp implements ServicioGenerico<Mercancia> {


    @Autowired
    Mercanciarepositorio mercanciarepositorio;

    @Autowired
    Zonarepositorio zonarepositorio;

    @Override
    public List<Mercancia> buscarTodos() throws Exception {
        try{

            List<Mercancia> mercancias= mercanciarepositorio.findAll();
            return mercancias;

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Mercancia buscarPorId(Integer id) throws Exception {
        try{

            Optional<Mercancia> mercancia= mercanciarepositorio.findById(id);
            return mercancia.get();

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Mercancia registrar(Mercancia tabla) throws Exception {
        try{

            Optional<Zona> zonaAsociada=zonarepositorio.findById(tabla.getZona().getId());
            Double capacidadDisponibleZona=zonaAsociada.get().getDisponible();
            Double capacidadOcupadaMercancia=tabla.getVolumen();
            Double diferenciaCapacidades=capacidadDisponibleZona-capacidadOcupadaMercancia;

            if(diferenciaCapacidades>=0){

                zonaAsociada.get().setDisponible(diferenciaCapacidades);
                zonarepositorio.save(zonaAsociada.get());
                tabla=mercanciarepositorio.save(tabla);
                return tabla;

            }else{
                throw new Exception("Esta zona no tiene capacidad para almacenar esta mercancia");
            }



        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Mercancia actualizar(Integer id, Mercancia tabla) throws Exception {
        try{

            Optional<Mercancia> mercanciaBuscada=mercanciarepositorio.findById(id);
            Mercancia mercancia=mercanciaBuscada.get();
            mercancia=mercanciarepositorio.save(tabla);

            return mercancia;

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Boolean borrar(Integer id) throws Exception {
        try{

            if(mercanciarepositorio.existsById(id)){

                Optional<Mercancia> mercancia=mercanciarepositorio.findById(id);
                Double capacidadOcupadaMercancia=mercancia.get().getVolumen();

                Integer idZonaAsociada=mercancia.get().getZona().getId();
                Optional<Zona> zonaAsociada=zonarepositorio.findById(idZonaAsociada);
                Double capacidadDisponibleZona=zonaAsociada.get().getDisponible();

                Double nuevaCapacidad=capacidadDisponibleZona+capacidadOcupadaMercancia;
                zonaAsociada.get().setDisponible(nuevaCapacidad);
                zonarepositorio.save(zonaAsociada.get());
                mercanciarepositorio.deleteById(id);

                return true;

            }else{

                throw new Exception("Id de mercancia no encontrado");

            }

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
}

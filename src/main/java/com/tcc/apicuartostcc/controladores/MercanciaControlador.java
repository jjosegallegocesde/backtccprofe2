package com.tcc.apicuartostcc.controladores;


import com.tcc.apicuartostcc.entidades.Mercancia;
import com.tcc.apicuartostcc.entidades.Zona;
import com.tcc.apicuartostcc.servicios.implementaciones.MercanciaServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tcc/mercancias")
public class MercanciaControlador {

    @Autowired
    MercanciaServicioImp mercanciaServicio;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrar(@RequestBody Mercancia mercancia){


        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mercanciaServicio.registrar(mercancia));
        }catch(Exception error){
            String json = "{\"error\":\"Error. Por favor revise: "+error+"\"}";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(json);
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mercanciaServicio.buscarTodos());
        }catch(Exception error){

            String json = "{\"error\":\"Error. Por favor revise: "+error+"\"}";

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(json);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mercanciaServicio.borrar(id));
        }catch(Exception error){
            String json = "{\"error\":\"Error. Por favor revise: "+error+"\"}";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(json);
        }
    }




}

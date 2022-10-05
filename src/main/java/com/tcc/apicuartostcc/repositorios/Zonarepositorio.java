package com.tcc.apicuartostcc.repositorios;

import com.tcc.apicuartostcc.entidades.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Zonarepositorio extends JpaRepository<Zona,Integer> {
}

package com.tcc.apicuartostcc.repositorios;

import com.tcc.apicuartostcc.entidades.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mercanciarepositorio extends JpaRepository<Mercancia,Integer> {
}

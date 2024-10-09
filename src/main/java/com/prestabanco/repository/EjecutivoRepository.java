package com.prestabanco.repository;

import com.prestabanco.entity.Ejecutivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjecutivoRepository extends JpaRepository<Ejecutivo, Long> {
    // Métodos personalizados si es necesario
}

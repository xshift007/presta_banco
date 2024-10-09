package com.prestabanco.repository;

import com.prestabanco.entity.EvaluacionCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionCreditoRepository extends JpaRepository<EvaluacionCredito, Long> {

    // Encuentra todas las evaluaciones realizadas por un ejecutivo específico
    List<EvaluacionCredito> findByEjecutivoIdEjecutivo(Long idEjecutivo);

    // Opcional: Buscar evaluaciones por estado (Aprobado/Rechazado)
    List<EvaluacionCredito> findByEstado(String estado);
}

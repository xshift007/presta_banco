package com.prestabanco.repository;

import com.prestabanco.entity.SimulacionCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulacionCreditoRepository extends JpaRepository<SimulacionCredito, Long> {

    // Encuentra todas las simulaciones de crédito por el ID del cliente
    List<SimulacionCredito> findByClienteIdCliente(Long idCliente);
}

package com.prestabanco.repository;

import com.prestabanco.entity.SolicitudCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudCreditoRepository extends JpaRepository<SolicitudCredito, Long> {
    List<SolicitudCredito> findByClienteIdCliente(Long idCliente);
    List<SolicitudCredito> findByEstado(String estado);
}

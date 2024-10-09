package com.prestabanco.service;

import com.prestabanco.entity.EvaluacionCredito;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.repository.EvaluacionCreditoRepository;
import com.prestabanco.repository.SolicitudCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluacionCreditoService {
    @Autowired

    private EvaluacionCreditoRepository evaluacionCreditoRepository;

    @Autowired
    private SolicitudCreditoRepository solicitudCreditoRepository;

    public EvaluacionCredito evaluarCredito(EvaluacionCredito evaluacion) {
        SolicitudCredito solicitud = evaluacion.getSolicitudCredito();

        if (evaluacion.getRelacionCuotaIngreso() <= 0.35 &&
                evaluacion.getRelacionDeudaIngreso() <= 0.50 &&
                evaluacion.getHistorialCrediticio().equalsIgnoreCase("Bueno") &&
                evaluacion.getAntiguedadLaboral() >= 12) {

            evaluacion.setEstado("Aprobado");
            solicitud.setEstado("Aprobado");
        } else {
            evaluacion.setEstado("Rechazado");
            solicitud.setEstado("Rechazado");
        }

        solicitudCreditoRepository.save(solicitud);
        return evaluacionCreditoRepository.save(evaluacion);
    }
}

package com.prestabanco.service;

import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.repository.ClienteRepository;
import com.prestabanco.repository.SolicitudCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudCreditoService {
    @Autowired
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public SolicitudCredito crearSolicitudCredito(Long idCliente, SolicitudCredito solicitudRequest) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + idCliente));

        solicitudRequest.setCliente(cliente);
        return solicitudCreditoRepository.save(solicitudRequest);
    }

    public List<SolicitudCredito> obtenerSolicitudesPorCliente(Long idCliente) {
        return solicitudCreditoRepository.findByClienteIdCliente(idCliente);
    }

    public List<SolicitudCredito> obtenerTodasLasSolicitudes() {
        return solicitudCreditoRepository.findAll();
    }

    public SolicitudCredito actualizarEstadoSolicitud(Long idSolicitud, String nuevoEstado) {
        SolicitudCredito solicitud = solicitudCreditoRepository.findById(idSolicitud)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + idSolicitud));

        solicitud.setEstado(nuevoEstado);
        return solicitudCreditoRepository.save(solicitud);
    }
}

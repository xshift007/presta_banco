package com.prestabanco.service;

import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SimulacionCredito;
import com.prestabanco.repository.ClienteRepository;
import com.prestabanco.repository.SimulacionCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulacionCreditoService {
    @Autowired
    private SimulacionCreditoRepository simulacionCreditoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public SimulacionCredito simularCredito(SimulacionCredito simulacionRequest) {
        // Validar y cargar el cliente
        Long clienteId = simulacionRequest.getCliente().getIdCliente();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId));

        // Asignar el cliente completo a la simulación
        simulacionRequest.setCliente(cliente);

        // Lógica de simulación
        Double monto = simulacionRequest.getMontoSimulado();
        Integer plazo = simulacionRequest.getPlazoSimulado();
        Double tasaInteres = 0.05; // Tasa de interés anual del 5%

        Double tasaMensual = tasaInteres / 12;
        int n = plazo * 12;

        Double cuotaMensual = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -n));

        simulacionRequest.setTasaInteres(tasaInteres);
        simulacionRequest.setCuotaMensual(Math.round(cuotaMensual * 100.0) / 100.0); // Redondear a 2 decimales
        simulacionRequest.setSeguroDesgravamen(Math.round(monto * 0.0005 * 100.0) / 100.0);
        simulacionRequest.setSeguroIncendio(Math.round(monto * 0.0003 * 100.0) / 100.0);
        simulacionRequest.setComisionAdministracion(50.0);

        return simulacionCreditoRepository.save(simulacionRequest);
    }

    public SimulacionCredito obtenerSimulacionPorId(Long idSimulacion) {
        return simulacionCreditoRepository.findById(idSimulacion)
                .orElseThrow(() -> new ResourceNotFoundException("Simulación no encontrada con ID: " + idSimulacion));
    }
}

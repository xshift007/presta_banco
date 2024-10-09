package com.prestabanco.controller;

import com.prestabanco.entity.SimulacionCredito;
import com.prestabanco.service.SimulacionCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/simulaciones")
public class SimulacionCreditoController {
    @Autowired
    private SimulacionCreditoService simulacionCreditoService;

    @PostMapping("/simular")
    public ResponseEntity<SimulacionCredito> simularCredito(@RequestBody SimulacionCredito simulacionRequest) {
        SimulacionCredito simulacion = simulacionCreditoService.simularCredito(simulacionRequest);
        return new ResponseEntity<>(simulacion, HttpStatus.CREATED);
    }

    @GetMapping("/{idSimulacion}")
    public ResponseEntity<SimulacionCredito> obtenerSimulacion(@PathVariable Long idSimulacion) {
        SimulacionCredito simulacion = simulacionCreditoService.obtenerSimulacionPorId(idSimulacion);
        return new ResponseEntity<>(simulacion, HttpStatus.OK);
    }

    // Otros endpoints si es necesario
}

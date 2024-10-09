package com.prestabanco.controller;

import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.service.ClienteService;
import com.prestabanco.service.SolicitudCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/solicitudes")
public class SolicitudCreditoController {
    @Autowired
    private SolicitudCreditoService solicitudCreditoService;

    @PostMapping("/clientes/{idCliente}")
    public ResponseEntity<SolicitudCredito> crearSolicitud(@PathVariable Long idCliente, @RequestBody SolicitudCredito solicitudRequest) {
        SolicitudCredito solicitud = solicitudCreditoService.crearSolicitudCredito(idCliente, solicitudRequest);
        return new ResponseEntity<>(solicitud, HttpStatus.CREATED);
    }

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<List<SolicitudCredito>> obtenerSolicitudesPorCliente(@PathVariable Long idCliente) {
        List<SolicitudCredito> solicitudes = solicitudCreditoService.obtenerSolicitudesPorCliente(idCliente);
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<SolicitudCredito>> obtenerTodasLasSolicitudes(@RequestHeader("rol") String rol) {
        if (!rol.equals("EJECUTIVO")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<SolicitudCredito> solicitudes = solicitudCreditoService.obtenerTodasLasSolicitudes();
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }


    @PutMapping("/{idSolicitud}/estado")
    public ResponseEntity<SolicitudCredito> actualizarEstadoSolicitud(@PathVariable Long idSolicitud, @RequestBody Map<String, String> estadoRequest) {
        String nuevoEstado = estadoRequest.get("estado");
        SolicitudCredito solicitudActualizada = solicitudCreditoService.actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
        return new ResponseEntity<>(solicitudActualizada, HttpStatus.OK);
    }
}
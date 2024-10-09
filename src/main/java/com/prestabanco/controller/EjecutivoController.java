package com.prestabanco.controller;

import com.prestabanco.entity.Ejecutivo;
import com.prestabanco.service.EjecutivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/ejecutivos")
public class EjecutivoController {
    @Autowired
    private EjecutivoService ejecutivoService;

    @PostMapping("/registrar")
    public ResponseEntity<Ejecutivo> registrarEjecutivo(@RequestBody Ejecutivo ejecutivo) {
        Ejecutivo nuevoEjecutivo = ejecutivoService.registrarEjecutivo(ejecutivo);
        return new ResponseEntity<>(nuevoEjecutivo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejecutivo> obtenerEjecutivo(@PathVariable Long id) {
        Ejecutivo ejecutivo = ejecutivoService.obtenerEjecutivoPorId(id);
        if (ejecutivo != null) {
            return new ResponseEntity<>(ejecutivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Ejecutivo>> obtenerTodosLosEjecutivos() {
        List<Ejecutivo> ejecutivos = ejecutivoService.obtenerTodosLosEjecutivos();
        return new ResponseEntity<>(ejecutivos, HttpStatus.OK);
    }
}

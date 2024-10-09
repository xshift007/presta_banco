package com.prestabanco.service;

import com.prestabanco.entity.Ejecutivo;
import com.prestabanco.repository.EjecutivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjecutivoService {
    @Autowired
    private EjecutivoRepository ejecutivoRepository;

    public Ejecutivo registrarEjecutivo(Ejecutivo ejecutivo) {
        return ejecutivoRepository.save(ejecutivo);
    }

    public Ejecutivo obtenerEjecutivoPorId(Long id) {
        return ejecutivoRepository.findById(id).orElse(null);
    }

    public List<Ejecutivo> obtenerTodosLosEjecutivos() {
        return ejecutivoRepository.findAll();
    }
}

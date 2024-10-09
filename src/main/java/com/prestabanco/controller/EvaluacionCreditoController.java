package com.prestabanco.controller;


import com.prestabanco.entity.EvaluacionCredito;
import com.prestabanco.service.EvaluacionCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionCreditoController {
    @Autowired
    private EvaluacionCreditoService evaluacionCreditoService;

    @PostMapping("/evaluar")
    public EvaluacionCredito evaluarCredito(@RequestBody EvaluacionCredito evaluacionRequest) {
        return evaluacionCreditoService.evaluarCredito(evaluacionRequest);
    }
}

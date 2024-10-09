package com.prestabanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestabanco.entity.Ejecutivo;
import com.prestabanco.entity.EvaluacionCredito;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.service.EvaluacionCreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EvaluacionCreditoController.class)
public class EvaluacionCreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluacionCreditoService evaluacionCreditoService;

    private EvaluacionCredito evaluacion;

    @BeforeEach
    public void setUp() {
        Ejecutivo ejecutivo = new Ejecutivo();
        ejecutivo.setIdEjecutivo(1L);

        SolicitudCredito solicitud = new SolicitudCredito();
        solicitud.setIdSolicitud(1L);

        evaluacion = new EvaluacionCredito();
        evaluacion.setIdEvaluacion(1L);
        evaluacion.setSolicitudCredito(solicitud);
        evaluacion.setEjecutivo(ejecutivo);
        evaluacion.setRelacionCuotaIngreso(0.3);
        evaluacion.setHistorialCrediticio("Bueno");
        evaluacion.setAntiguedadLaboral(24);
        evaluacion.setRelacionDeudaIngreso(0.4);
        evaluacion.setEstado("Aprobado");
    }

    @Test
    public void evaluarCreditoTest() throws Exception {
        when(evaluacionCreditoService.evaluarCredito(any(EvaluacionCredito.class))).thenReturn(evaluacion);

        mockMvc.perform(post("/evaluaciones/evaluar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(evaluacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEvaluacion").value(1L))
                .andExpect(jsonPath("$.estado").value("Aprobado"));

        verify(evaluacionCreditoService, times(1)).evaluarCredito(any(EvaluacionCredito.class));
    }

    // Método auxiliar
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

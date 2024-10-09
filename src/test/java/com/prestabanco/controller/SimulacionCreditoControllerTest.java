package com.prestabanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SimulacionCredito;
import com.prestabanco.service.SimulacionCreditoService;
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

@WebMvcTest(SimulacionCreditoController.class)
public class SimulacionCreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimulacionCreditoService simulacionCreditoService;

    private SimulacionCredito simulacion;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        simulacion = new SimulacionCredito();
        simulacion.setIdSimulacion(1L);
        simulacion.setCliente(cliente);
        simulacion.setMontoSimulado(200000.0);
        simulacion.setPlazoSimulado(30);
        // Otros campos...
    }

    @Test
    public void simularCreditoTest() throws Exception {
        when(simulacionCreditoService.simularCredito(any(SimulacionCredito.class))).thenReturn(simulacion);

        mockMvc.perform(post("/simulaciones/simular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(simulacion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idSimulacion").value(1L))
                .andExpect(jsonPath("$.montoSimulado").value(200000.0));

        verify(simulacionCreditoService, times(1)).simularCredito(any(SimulacionCredito.class));
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

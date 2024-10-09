package com.prestabanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.service.SolicitudCreditoService;
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

@WebMvcTest(SolicitudCreditoController.class)
public class SolicitudCreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolicitudCreditoService solicitudCreditoService;

    private SolicitudCredito solicitud;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        solicitud = new SolicitudCredito();
        solicitud.setIdSolicitud(1L);
        solicitud.setCliente(cliente);
        solicitud.setMontoSolicitado(200000.0);
        solicitud.setPlazo(30);
        solicitud.setTipoCredito("Primera Vivienda");
        solicitud.setEstado("Pendiente");
        // Otros campos...
    }

    @Test
    public void solicitarCreditoTest() throws Exception {
        when(solicitudCreditoService.solicitarCredito(any(SolicitudCredito.class))).thenReturn(solicitud);

        mockMvc.perform(post("/solicitudes/solicitar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(solicitud)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSolicitud").value(1L))
                .andExpect(jsonPath("$.estado").value("Pendiente"));

        verify(solicitudCreditoService, times(1)).solicitarCredito(any(SolicitudCredito.class));
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

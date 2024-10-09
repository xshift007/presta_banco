package com.prestabanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestabanco.entity.Cliente;
import com.prestabanco.service.ClienteService;
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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        // Otros campos...
    }

    @Test
    public void registrarClienteTest() throws Exception {
        when(clienteService.registrarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(clienteService, times(1)).registrarCliente(any(Cliente.class));
    }

    @Test
    public void obtenerClienteTest() throws Exception {
        when(clienteService.obtenerClientePorId(1L)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(clienteService, times(1)).obtenerClientePorId(1L);
    }

    // Método auxiliar para convertir objetos a JSON
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

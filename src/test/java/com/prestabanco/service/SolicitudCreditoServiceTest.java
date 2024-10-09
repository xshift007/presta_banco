package com.prestabanco.service;

import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.repository.ClienteRepository;
import com.prestabanco.repository.SolicitudCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SolicitudCreditoServiceTest {

    @Mock
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private SolicitudCreditoService solicitudCreditoService;

    private Cliente cliente;
    private SolicitudCredito solicitud;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Juan");

        solicitud = new SolicitudCredito();
        solicitud.setCliente(cliente);
        solicitud.setMontoSolicitado(200000.0);
        solicitud.setPlazo(30);
        solicitud.setTipoCredito("Primera Vivienda");
    }

    @Test
    public void solicitarCreditoTest() {
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));
        when(solicitudCreditoRepository.save(any(SolicitudCredito.class))).thenReturn(solicitud);

        SolicitudCredito nuevaSolicitud = solicitudCreditoService.solicitarCredito(solicitud);

        assertNotNull(nuevaSolicitud);
        assertEquals("Pendiente", nuevaSolicitud.getEstado());
        assertNotNull(nuevaSolicitud.getFechaSolicitud());
        verify(solicitudCreditoRepository, times(1)).save(solicitud);
    }

    @Test
    public void solicitarCreditoClienteNoEncontradoTest() {
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            solicitudCreditoService.solicitarCredito(solicitud);
        });

        String expectedMessage = "Cliente no encontrado con ID: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(solicitudCreditoRepository, never()).save(any(SolicitudCredito.class));
    }
}

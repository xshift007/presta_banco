package com.prestabanco.service;

import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.entity.Cliente;
import com.prestabanco.entity.SimulacionCredito;
import com.prestabanco.repository.ClienteRepository;
import com.prestabanco.repository.SimulacionCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SimulacionCreditoServiceTest {

    @Mock
    private SimulacionCreditoRepository simulacionCreditoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private SimulacionCreditoService simulacionCreditoService;

    private Cliente cliente;
    private SimulacionCredito simulacionRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Juan");

        simulacionRequest = new SimulacionCredito();
        simulacionRequest.setCliente(cliente);
        simulacionRequest.setMontoSimulado(200000.0);
        simulacionRequest.setPlazoSimulado(30);
    }

    @Test
    public void simularCreditoTest() {
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));
        when(simulacionCreditoRepository.save(any(SimulacionCredito.class))).thenReturn(simulacionRequest);

        SimulacionCredito simulacion = simulacionCreditoService.simularCredito(simulacionRequest);

        assertNotNull(simulacion);
        assertEquals(200000.0, simulacion.getMontoSimulado());
        assertEquals(30, simulacion.getPlazoSimulado());
        assertEquals(0.05, simulacion.getTasaInteres());
        verify(simulacionCreditoRepository, times(1)).save(simulacionRequest);
    }

    @Test
    public void simularCreditoClienteNoEncontradoTest() {
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            simulacionCreditoService.simularCredito(simulacionRequest);
        });

        String expectedMessage = "Cliente no encontrado con ID: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(simulacionCreditoRepository, never()).save(any(SimulacionCredito.class));
    }
}

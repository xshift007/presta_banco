package com.prestabanco.service;

import com.prestabanco.entity.Cliente;
import com.prestabanco.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setEmail("juan.perez@example.com");
        // Otros campos...
    }

    @Test
    public void registrarClienteTest() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente nuevoCliente = clienteService.registrarCliente(cliente);

        assertNotNull(nuevoCliente);
        assertEquals("Juan", nuevoCliente.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void obtenerClientePorIdTest() {
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.obtenerClientePorId(1L);

        assertNotNull(clienteEncontrado);
        assertEquals(1L, clienteEncontrado.getIdCliente());
        verify(clienteRepository, times(1)).findById(1L);
    }
}

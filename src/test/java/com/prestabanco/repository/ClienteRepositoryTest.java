package com.prestabanco.repository;

import com.prestabanco.entity.Cliente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setEmail("juan.perez@example.com");
        // Otros campos...
        clienteRepository.save(cliente);
    }

    @Test
    public void findByIdTest() {
        Optional<Cliente> found = clienteRepository.findById(cliente.getIdCliente());
        assertTrue(found.isPresent());
        assertEquals("Juan", found.get().getNombre());
    }

    @Test
    public void saveTest() {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("María");
        nuevoCliente.setApellido("García");
        clienteRepository.save(nuevoCliente);

        Optional<Cliente> found = clienteRepository.findById(nuevoCliente.getIdCliente());
        assertTrue(found.isPresent());
        assertEquals("María", found.get().getNombre());
    }
}

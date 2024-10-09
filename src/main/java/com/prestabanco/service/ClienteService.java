package com.prestabanco.service;

import com.prestabanco.entity.Cliente;
import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente login(String usuario, String password) {
        Cliente cliente = clienteRepository.findByUsuario(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (cliente.getPassword().equals(password)) {
            return cliente;
        } else {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
    }

    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente obtenerClientePorUsuario(String usuario) {
        return clienteRepository.findByUsuario(usuario).orElse(null);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente actualizarCliente(Long idCliente, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + idCliente));

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setApellido(clienteActualizado.getApellido());
        cliente.setEmail(clienteActualizado.getEmail());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setDireccion(clienteActualizado.getDireccion());
        // No actualizar usuario, password ni rol aquí

        return clienteRepository.save(cliente);
    }

    public void cambiarPassword(Long idCliente, String passwordActual, String nuevoPassword) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + idCliente));

        if (!cliente.getPassword().equals(passwordActual)) {
            throw new IllegalArgumentException("La contraseña actual no es correcta");
        }

        cliente.setPassword(nuevoPassword);
        clienteRepository.save(cliente);
    }




}

package com.prestabanco.controller;

import com.prestabanco.entity.Cliente;
import com.prestabanco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000") // Asegura que CORS permite solicitudes desde el frontend
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestBody Map<String, String> loginData) {
        String usuario = loginData.get("usuario");
        String password = loginData.get("password");
        Cliente cliente = clienteService.obtenerClientePorUsuario(usuario);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Cliente> obtenerClientePorUsuario(@PathVariable String usuario) {
        Cliente cliente = clienteService.obtenerClientePorUsuario(usuario);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long idCliente, @RequestBody Cliente clienteActualizado) {
        Cliente cliente = clienteService.actualizarCliente(idCliente, clienteActualizado);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{idCliente}/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@PathVariable Long idCliente, @RequestBody Map<String, String> passwords) {

        String passwordActual = passwords.get("passwordActual");
        String nuevoPassword = passwords.get("nuevoPassword");

        clienteService.cambiarPassword(idCliente, passwordActual, nuevoPassword);
        return new ResponseEntity<>("Contraseña actualizada correctamente", HttpStatus.OK);
    }



}

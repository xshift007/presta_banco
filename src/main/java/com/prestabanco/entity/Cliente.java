// src/main/java/com/prestabanco/entity/Cliente.java

package com.prestabanco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private Double ingresos;
    private String tipoCliente;
    private String usuario;

    private String password;
    private String rol; // Valores posibles: "CLIENTE", "EJECUTIVO"


    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Añadir esta anotación
    private List<SolicitudCredito> solicitudesCredito = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Añadir esta anotación
    private List<SimulacionCredito> simulacionesCredito = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        fechaRegistro = new Date();
    }
}
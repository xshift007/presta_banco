package com.prestabanco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ejecutivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejecutivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEjecutivo;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String usuario;

    @JsonIgnore // Para no exponer la contraseña
    private String password;

    @OneToMany(mappedBy = "ejecutivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Para evitar recursión infinita
    private List<EvaluacionCredito> evaluacionesCredito;
}
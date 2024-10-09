// src/main/java/com/prestabanco/entity/TipoPrestamo.java

package com.prestabanco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "evaluaciones_credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    @OneToOne
    @JoinColumn(name = "id_solicitud")
    private SolicitudCredito solicitudCredito;

    @ManyToOne
    @JoinColumn(name = "id_ejecutivo")
    private Ejecutivo ejecutivo;

    private Double relacionCuotaIngreso;
    private String historialCrediticio;
    private Integer antiguedadLaboral; // en meses
    private Double relacionDeudaIngreso;
    private String estado; // Aprobado o Rechazado
}

// src/main/java/com/prestabanco/entity/SolicitudCredito.java

package com.prestabanco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "solicitudes_credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    private Double montoSolicitado;
    private Integer plazoSolicitado; // en años
    private String estado; // Ejemplo: "Pendiente", "Aprobado", "Rechazado"

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @PrePersist
    protected void onCreate() {
        fechaSolicitud = new Date();
        estado = "Pendiente";
    }
}
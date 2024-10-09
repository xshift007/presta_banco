// src/main/java/com/prestabanco/entity/SimulacionCredito.java

package com.prestabanco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "simulaciones_credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulacionCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSimulacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    private Double montoSimulado;
    private Integer plazoSimulado; // en años
    private Double tasaInteres;
    private Double cuotaMensual;
    private Double seguroDesgravamen;
    private Double seguroIncendio;
    private Double comisionAdministracion;
}
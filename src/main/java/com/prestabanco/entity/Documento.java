// src/main/java/com/prestabanco/entity/Documento.java

package com.prestabanco.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @ManyToOne
    @JoinColumn(name = "idSolicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    private String tipoDocumento;
    private String urlDocumento;
    private LocalDate fechaCarga;
}

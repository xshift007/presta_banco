package com.prestabanco.service;

import com.prestabanco.exception.ResourceNotFoundException;
import com.prestabanco.entity.Ejecutivo;
import com.prestabanco.entity.EvaluacionCredito;
import com.prestabanco.entity.SolicitudCredito;
import com.prestabanco.repository.EvaluacionCreditoRepository;
import com.prestabanco.repository.SolicitudCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EvaluacionCreditoServiceTest {

    @Mock
    private EvaluacionCreditoRepository evaluacionCreditoRepository;

    @Mock
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @InjectMocks
    private EvaluacionCreditoService evaluacionCreditoService;

    private EvaluacionCredito evaluacion;
    private SolicitudCredito solicitud;
    private Ejecutivo ejecutivo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ejecutivo = new Ejecutivo();
        ejecutivo.setIdEjecutivo(1L);
        ejecutivo.setNombre("María");

        solicitud = new SolicitudCredito();
        solicitud.setIdSolicitud(1L);
        solicitud.setEstado("Pendiente");

        evaluacion = new EvaluacionCredito();
        evaluacion.setSolicitudCredito(solicitud);
        evaluacion.setEjecutivo(ejecutivo);
        evaluacion.setRelacionCuotaIngreso(0.3);
        evaluacion.setHistorialCrediticio("Bueno");
        evaluacion.setAntiguedadLaboral(24);
        evaluacion.setRelacionDeudaIngreso(0.4);
    }

    @Test
    public void evaluarCreditoAprobadoTest() {
        when(solicitudCreditoRepository.save(any(SolicitudCredito.class))).thenReturn(solicitud);
        when(evaluacionCreditoRepository.save(any(EvaluacionCredito.class))).thenReturn(evaluacion);

        EvaluacionCredito resultado = evaluacionCreditoService.evaluarCredito(evaluacion);

        assertNotNull(resultado);
        assertEquals("Aprobado", resultado.getEstado());
        assertEquals("Aprobado", solicitud.getEstado());
        verify(evaluacionCreditoRepository, times(1)).save(evaluacion);
        verify(solicitudCreditoRepository, times(1)).save(solicitud);
    }

    @Test
    public void evaluarCreditoRechazadoTest() {
        evaluacion.setRelacionCuotaIngreso(0.5); // Mayor al límite permitido

        when(solicitudCreditoRepository.save(any(SolicitudCredito.class))).thenReturn(solicitud);
        when(evaluacionCreditoRepository.save(any(EvaluacionCredito.class))).thenReturn(evaluacion);

        EvaluacionCredito resultado = evaluacionCreditoService.evaluarCredito(evaluacion);

        assertNotNull(resultado);
        assertEquals("Rechazado", resultado.getEstado());
        assertEquals("Rechazado", solicitud.getEstado());
        verify(evaluacionCreditoRepository, times(1)).save(evaluacion);
        verify(solicitudCreditoRepository, times(1)).save(solicitud);
    }
}

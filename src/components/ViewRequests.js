import React, { useState, useEffect } from 'react';
import api from '../services/api';

function ViewRequests({ clienteId }) {
  const [solicitudes, setSolicitudes] = useState([]);

  useEffect(() => {
    const fetchSolicitudes = async () => {
      try {
        const response = await api.get(`/solicitudes/cliente/${clienteId}`);
        setSolicitudes(response.data);
      } catch (error) {
        alert('Error al obtener las solicitudes');
        console.error(error);
      }
    };

    fetchSolicitudes();
  }, [clienteId]);

  return (
    <div>
      <h2>Mis Solicitudes de Crédito</h2>
      {solicitudes.length === 0 ? (
        <p>No tienes solicitudes</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID Solicitud</th>
              <th>Monto</th>
              <th>Plazo</th>
              <th>Tipo de Crédito</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            {solicitudes.map((solicitud) => (
              <tr key={solicitud.idSolicitud}>
                <td>{solicitud.idSolicitud}</td>
                <td>{solicitud.montoSolicitado}</td>
                <td>{solicitud.plazo}</td>
                <td>{solicitud.tipoCredito}</td>
                <td>{solicitud.estado}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ViewRequests;

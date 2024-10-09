import React, { useState } from 'react';
import api from '../services/api';

function SimulateCredit({ clienteId }) {
  const [simulacion, setSimulacion] = useState({
    montoSimulado: '',
    plazoSimulado: '',
  });

  const [resultado, setResultado] = useState(null);

  const handleChange = (e) => {
    setSimulacion({ ...simulacion, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/simulaciones/simular', {
        cliente: { idCliente: clienteId },
        montoSimulado: parseFloat(simulacion.montoSimulado),
        plazoSimulado: parseInt(simulacion.plazoSimulado),
      });
      setResultado(response.data);
    } catch (error) {
      alert('Error al simular el crédito');
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Simular Crédito</h2>
      <form onSubmit={handleSubmit}>
        <input type="number" name="montoSimulado" placeholder="Monto" onChange={handleChange} required />
        <input type="number" name="plazoSimulado" placeholder="Plazo en años" onChange={handleChange} required />
        <button type="submit">Simular</button>
      </form>
      {resultado && (
        <div>
          <h3>Resultado de la Simulación</h3>
          <p>Cuota Mensual: {resultado.cuotaMensual}</p>
          <p>Tasa de Interés: {resultado.tasaInteres * 100}%</p>
          <p>Seguro Desgravamen: {resultado.seguroDesgravamen}</p>
          <p>Seguro Incendio: {resultado.seguroIncendio}</p>
          <p>Comisión Administración: {resultado.comisionAdministracion}</p>
        </div>
      )}
    </div>
  );
}

export default SimulateCredit;

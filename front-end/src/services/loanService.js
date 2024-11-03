// src/services/loanService.js
//import axios from 'axios';
// src/services/loanService.js
import api from './api';


// Simular préstamo (P1)
const simulateLoan = (data) => {
  return api.post(`${API_URL}/simular`, data);
};

// Crear solicitud de crédito (P3)
const createLoanApplication = (formData) => {
  return api.post(`${API_URL}/crear-con-usuario`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// Obtener todas las solicitudes (P4 - para evaluación)
const getAllSolicitudes = () => {
  return api.get(`${API_URL}`);
};

// Evaluar una solicitud (P4 - para evaluación)
const evaluateLoan = (idSolicitud) => {
  return api.put(`${API_URL}/${idSolicitud}/evaluar`);
};

// Obtener solicitudes por nombre de usuario (P5 - para seguimiento)
const getSolicitudesByUser = (nombreCompleto) => {
  return api.get(`${API_URL}/usuario/nombre/${nombreCompleto}`);
};

export default {
  simulateLoan,
  createLoanApplication,
  getAllSolicitudes,
  evaluateLoan,
  getSolicitudesByUser,
};

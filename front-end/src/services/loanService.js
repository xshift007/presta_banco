// src/services/loanService.js
import axios from 'axios';

// Asegúrate de que esta URL apunta a tu backend
const API_URL = 'http://localhost:8080/solicitudes'; // Cambia 8080 por el puerto de tu backend si es diferente

// Simular préstamo (P1)
const simulateLoan = (data) => {
  return axios.post(`${API_URL}/simular`, data);
};

// Crear solicitud de crédito (P3)
const createLoanApplication = (formData) => {
  return axios.post(`${API_URL}/crear-con-usuario`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// Obtener todas las solicitudes (P4 - para evaluación)
const getAllSolicitudes = () => {
  return axios.get(`${API_URL}`);
};

// Evaluar una solicitud (P4 - para evaluación)
const evaluateLoan = (idSolicitud) => {
  return axios.put(`${API_URL}/${idSolicitud}/evaluar`);
};

// Obtener solicitudes por nombre de usuario (P5 - para seguimiento)
const getSolicitudesByUser = (nombreCompleto) => {
  return axios.get(`${API_URL}/usuario/nombre/${nombreCompleto}`);
};

export default {
  simulateLoan,
  createLoanApplication,
  getAllSolicitudes,
  evaluateLoan,
  getSolicitudesByUser,
};

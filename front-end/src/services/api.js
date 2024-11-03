// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Ajusta el puerto si es necesario
});

export default api;

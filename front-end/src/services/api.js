// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'VITE_BACKEND_URL_PLACEHOLDER',
});

export default api;

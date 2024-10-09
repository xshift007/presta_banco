import React, { useState } from 'react';
import api from '../services/api';

function Login({ setClienteId }) {
  const [credentials, setCredentials] = useState({
    usuario: '',
    password: '',
  });

  const [error, setError] = useState(null);

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const response = await api.post('/clientes/login', credentials);
      if (response.status === 200) {
        alert('Inicio de sesión exitoso');
        setClienteId(response.data.idCliente);
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError('Usuario o contraseña incorrectos');
      } else {
        setError('Error al iniciar sesión');
      }
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Inicio de Sesión</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="usuario"
          placeholder="Usuario"
          value={credentials.usuario}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Contraseña"
          value={credentials.password}
          onChange={handleChange}
          required
        />
        <button type="submit">Ingresar</button>
      </form>
    </div>
  );
}

export default Login;

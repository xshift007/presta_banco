import React, { useState } from 'react';
import api from '../services/api';

function Register() {
  const [cliente, setCliente] = useState({
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    direccion: '',
    ingresos: '',
    tipoCliente: '',
    usuario: '',
    password: '',
  });

  const handleChange = (e) => {
    setCliente({ ...cliente, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post('/clientes/registrar', cliente);
      alert('Registro exitoso');
      console.log(response.data);
    } catch (error) {
      if (error.response && error.response.data && error.response.data.message) {
        alert(`Error: ${error.response.data.message}`);
      } else {
        alert('Error al registrar el cliente');
      }
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Registro de Cliente</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="nombre" placeholder="Nombre" onChange={handleChange} required />
        <input type="text" name="apellido" placeholder="Apellido" onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" onChange={handleChange} required />
        <input type="text" name="telefono" placeholder="Teléfono" onChange={handleChange} required />
        <input type="text" name="direccion" placeholder="Dirección" onChange={handleChange} required />
        <input type="number" name="ingresos" placeholder="Ingresos" onChange={handleChange} required />
        <input type="text" name="tipoCliente" placeholder="Tipo de Cliente" onChange={handleChange} required />
        <input type="text" name="usuario" placeholder="Usuario" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Contraseña" onChange={handleChange} required />
        <button type="submit">Registrarse</button>
      </form>
    </div>
  );
}

export default Register;

import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import SimulateCredit from './components/SimulateCredit';
import ViewRequests from './components/ViewRequests';
import './App.css';

function App() {
  const [clienteId, setClienteId] = useState(null);

  return (
    <Router>
      <div className="app-container">
        <header className="header">
          <h1>PrestaBanco</h1>
          <nav>
            <ul className="nav-list">
              {!clienteId ? (
                <>
                  <li>
                    <Link to="/register">Registrarse</Link>
                  </li>
                  <li>
                    <Link to="/login">Iniciar Sesión</Link>
                  </li>
                </>
              ) : (
                <>
                  <li>
                    <Link to="/simulate">Simular Crédito</Link>
                  </li>
                  <li>
                    <Link to="/requests">Mis Solicitudes</Link>
                  </li>
                  <li>
                    <button className="logout-button" onClick={() => setClienteId(null)}>Cerrar Sesión</button>
                  </li>
                </>
              )}
            </ul>
          </nav>
        </header>

        <main className="main-content">
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login setClienteId={setClienteId} />} />
            {clienteId && (
              <>
                <Route path="/simulate" element={<SimulateCredit clienteId={clienteId} />} />
                <Route path="/requests" element={<ViewRequests clienteId={clienteId} />} />
              </>
            )}
            <Route path="/" element={<h2>Bienvenido a PrestaBanco</h2>} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
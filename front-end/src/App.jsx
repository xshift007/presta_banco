/*
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
*/

import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Simulation from './components/Simulation';
import UserRegistration from './components/UserRegistration';
import LoanApplication from './components/LoanApplication';
import LoanStatus from './components/LoanStatus';
import LoanEvaluation from './components/LoanEvaluation';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/simulacion" element={<Simulation />} />
        <Route path="/registro-usuario" element={<UserRegistration />} />
        <Route path="/solicitud-credito" element={<LoanApplication />} />
        <Route path="/estado-solicitudes" element={<LoanStatus />} />
        <Route path="/evaluacion-solicitudes" element={<LoanEvaluation />} />
      </Routes>
    </Router>
  );
}

export default App;



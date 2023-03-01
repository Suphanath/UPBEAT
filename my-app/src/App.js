import React from 'react';
import HexGridDemo from "./Grid.js";

export default function App() {
  return (<div style={{backgroundColor: '#98730c'}}>
    <h1 style={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      color:'white'
    }}>UPBEAT</h1>
    <div className="App" style={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      height: '100vh',
      marginTop: "50px"
    }}>
      <HexGridDemo />
      </div>
    </div>
  );
}
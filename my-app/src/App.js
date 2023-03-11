import React from 'react';
import './App.css';
import Hexagon from "./components/Hexagon";

function App() {
  const board = [];
  // generate rows
  for (let i = 0; i < 8; i++) {
    const row = [];
    // generate columns
    for (let j = 0; j < 8; j++) {
      row.push(<div><Hexagon /></div>);
    }
    board.push(<div><Hexagon /></div>);
  }

  return (
    <div className="App">
      <h1 style={{marginBottom: 50}}>UPBEAT</h1>
      {board}
    </div>
  );
}

export default App;

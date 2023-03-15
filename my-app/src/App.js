import React from 'react';
import './App.css';
import './components/hexagon.css';
import Play from './Play';
import Main from './Main';

function App() {
  const headingStyle = {
    justifyContent: 'center',
    display: 'flex',
    fontFamily: 'Geostar Fill',
    color: '#FFE600',
    fontSize: '50px',
    margin: 100,
  };
  return (
    <div>
       <h1 style={headingStyle}>UPBEAT</h1>
        <ul style={{ display: 'flex',textAlign: 'center', justifyContent: 'center', margin: 200 , fontSize: 35}}>
            <a href="/play" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>Play</a>
        </ul>
        <ul style={{textAlign: 'center', display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
            <a href="/how-to-play" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>How to play</a>
        </ul> 
    </div>
  );
}

  const link = document.createElement('link');
  link.href = 'https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap';
  link.rel = 'stylesheet';
  document.head.appendChild(link);

export default App;

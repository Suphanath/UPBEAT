import React from 'react';
import './App.css';

function Main() {
  const headingStyle = {
    textAlign: 'center',
    fontFamily: 'Geostar Fill',
    fontSize: '4rem',
    color: '#FFE600',
    fontSize: '50px',
    margin: 100,
  };

  return (
    <div>
        <h1 style={headingStyle}>UPBEAT</h1>
        <ul style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a href="/play" style={{textDecoration: 'none', color: '#BBFF85',fontFamily: 'Geostar Fill'}}>Player1</a>
          </li>
        </ul>
        <ul style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a href="/play" style={{textDecoration: 'none', color: '#BBFF85',fontFamily: 'Geostar Fill'}}>Player2</a>
          </li>
        </ul>
        <ul style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a href="/play" style={{textDecoration: 'none', color: '#BBFF85',fontFamily: 'Geostar Fill'}}>Player3</a>
          </li>
        </ul>
        <ul style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a href="/play" style={{textDecoration: 'none', color: '#BBFF85',fontFamily: 'Geostar Fill'}}>Player4</a>
          </li>
        </ul>
        <ul style={{ display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a href="/how-to-play" style={{textDecoration: 'none', color: 'red',fontFamily: 'Geostar Fill'}}>Back to menu</a>
          </li>
        </ul> 
    </div>
  );
}
const link = document.createElement('link');
  link.href = 'https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap';
  link.rel = 'stylesheet';
  document.head.appendChild(link);

export default Main;
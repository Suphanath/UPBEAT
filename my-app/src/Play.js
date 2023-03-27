import React from 'react';
import './Play.css';
import Hexagon from "./components/Hexagon";

const Play = () => {
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

  const containerStyle = {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  };

  const boardContainerStyle = {
    flex: 1,
  };

  const textContainerStyle = {
    marginRight: '150px',
  };

  const headingStyle = {
    textAlign: 'center',
    fontFamily: 'Geostar Fill',
    fontSize: '4rem',
    color: '#FFE600',
    fontSize: '50px',
    margin: 100,
  };

  const buttonStyle = {
    display: 'block',
    margin: '0 auto',
    marginBottom: '50px',
    fontSize: '25px',
  };

  const textAreaStyle = {
    width: '100%',
    height: '500px',
    marginBottom: 20,
  };

  return (
    <div>
      <div style={containerStyle}>
        <div style={boardContainerStyle}>
          {board}
        </div>
        <div style={textContainerStyle}>
          <label>
            <textarea type="text" style={textAreaStyle}/>
          </label>
          <button style={buttonStyle}>Submit</button>
          <a href="/">Go to main (for dev)</a>
        </div>
      </div>
    </div>
  );
}

const link = document.createElement('link');
link.href = 'https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap';
link.rel = 'stylesheet';
document.head.appendChild(link);

export default Play;

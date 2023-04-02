import { Link } from 'react-router-dom'
import './App.css';
import { useState } from 'react';

const Player = () => {

  const [player1Style, setPlayer1Style] = useState({
    textDecoration: 'none',
    color: '#BBFF85',
    fontFamily: 'Geostar Fill',
  });
  const [player2Style, setPlayer2Style] = useState({
    textDecoration: 'none',
    color: '#BBFF85',
    fontFamily: 'Geostar Fill',
  });
  // const [player3Style, setPlayer3Style] = useState({
  //   textDecoration: 'none',
  //   color: '#BBFF85',
  //   fontFamily: 'Geostar Fill',
  // });
  // const [player4Style, setPlayer4Style] = useState({
  //   textDecoration: 'none',
  //   color: '#BBFF85',
  //   fontFamily: 'Geostar Fill',
  // });

  const [canStart, setCanStart] = useState(false);

  const handlePlayerClick = (player) => {
    switch (player) {
      case 'player1':
        setPlayer1Style({
          textDecoration: 'underline',
          color: '#A9A9A9',
          fontFamily: 'Geostar Fill',
        });
        break;
      case 'player2':
        setPlayer2Style({
          textDecoration: 'underline',
          color: '#A9A9A9',
          fontFamily: 'Geostar Fill',
        });
        break;
      // case 'player3':
      //   setPlayer3Style({
      //     textDecoration: 'underline',
      //     color: '#A9A9A9',
      //     fontFamily: 'Geostar Fill',
      //   });
      //   break;
      // case 'player4':
      //   setPlayer4Style({
      //     textDecoration: 'underline',
      //     color: '#A9A9A9',
      //     fontFamily: 'Geostar Fill',
      //   });
      //   break;
      default:
        break;
    }

    if (player1Style.textDecoration === 'underline' && player2Style.textDecoration === 'underline') {
      setCanStart(true);
    } else {
      setCanStart(false);
    }
  };

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
        <div style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <div style={{ margin: '0 10px' }}>
            <a  style={player1Style} onClick={() => handlePlayerClick('player1')}>Player 1</a>
          </div>
        </div>
        <div style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <div style={{ margin: '0 10px' }}>
            <a  style={player2Style} onClick={() => handlePlayerClick('player2')}>Player 2</a>
          </div>
        </div>
        {/* <div style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a  style={player3Style} onClick={() => handlePlayerClick('player3')}>Player3</a>
          </li>
        </div>
        <div style={{ display: 'flex', justifyContent: 'center', margin: 15 , fontSize: 35}}>
          <li style={{ margin: '0 10px' }}>
            <a  style={player4Style} onClick={() => handlePlayerClick('player4')}>Player4</a>
          </li>
        </div> */}
        <div style={{ display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
          <div style={{ margin: '0 10px' }}>
          {canStart ? (
            <Link to="/play" style={{ textDecoration: 'none', color: '#FFE600', fontFamily: 'Geostar Fill' }}>Start!</Link>
          ) : (
            <span style={{ color: 'gray', textDecoration: 'line-through', fontFamily: 'Geostar Fill' }}>Start!</span>
          )}
          </div>
        </div> 
        <div style={{ display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
          <div style={{ margin: '0 10px' }}>
            <Link to="/" style={{textDecoration: 'none', color: 'red',fontFamily: 'Geostar Fill'}}>Back to menu</Link>
          </div>
        </div> 
    </div>
  );
}
const link = document.createElement('link');
  link.href = 'https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap';
  link.rel = 'stylesheet';
  document.head.appendChild(link);

export default Player;
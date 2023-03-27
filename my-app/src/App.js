import './App.css';
import './components/hexagon.css';
import Main from './Main';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Player from './Player';
import Play from './Play';

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
    <Router>
      <div>
        <h1 style={headingStyle}>UPBEAT</h1>
        <Switch>
          <Route exact path="/"> 
            <Main/>
          </Route>
          <Route path="/player"> 
            <Player/>
          </Route>
          <Route path="/play"> 
            <Play/>
          </Route>
        </Switch>  
      </div>
    </Router>
  );
}

  const link = document.createElement('link');
  link.href = 'https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap';
  link.rel = 'stylesheet';
  document.head.appendChild(link);

export default App;
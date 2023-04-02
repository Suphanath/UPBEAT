import { Link } from 'react-router-dom';
import './Howtoplay.css';

const Howtoplay = () => {
  return (
    <div className="Howtoplay-container">
      <div className="Howtoplay-header"style={{textAlign: 'center', display: 'flex', justifyContent: 'center', fontSize: 35}}>How to play:</div>
      <div className="Howtoplay-info" style={{textAlign: 'center', display: 'flex', justifyContent: 'center', fontSize: 35}}>At the beginning of each turn, each of the regions belonging to the current player accrues interest, and then the timer for that player resumes counting down.  During this time, the player can revise their construction plan.  If the player is satisfied with the existing construction plan, they can confirm it, at which point the timer pauses, and the city crew of that player embarks from the city center, performing tasks according to the construction plan until finished.  Then, the next player's turn begins.</div>
      <Link to="/" className="Howtoplay-back-link" style={{color:'red', textAlign: 'center', display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>Back to menu</Link>
    </div>
  );
}

export default Howtoplay;

const link = document.createElement('link');
link.href = 'https://fonts.googleapis.com/css2?family=Oswald&display=swap';
link.rel = 'stylesheet';
document.head.appendChild(link);
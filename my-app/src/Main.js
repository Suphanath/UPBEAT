const Main = () => {
    return(
        <div>
           <ul style={{ display: 'flex',textAlign: 'center', justifyContent: 'center', margin: 200 , fontSize: 35}}>
            <a href="/player" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>Play</a>
        </ul>
        <ul style={{textAlign: 'center', display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
            <a href="/how-to-play" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>How to play</a>
        </ul>  
        </div>
    );
}

export default Main;
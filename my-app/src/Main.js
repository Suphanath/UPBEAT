const Main = () => {
    return(
        <div>
           <div style={{ display: 'flex',textAlign: 'center', justifyContent: 'center', margin: 200 , fontSize: 35}}>
            <a href="/player" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>Play</a>
        </div>
        <div style={{textAlign: 'center', display: 'flex', justifyContent: 'center', margin: 100 , fontSize: 35}}>
            <a href="/howtoplay" style={{textAlign: 'center',textDecoration: 'none', color: 'white',fontFamily: 'Geostar Fill'}}>How to play</a>
            </div>  
        </div>
    );
}

export default Main;
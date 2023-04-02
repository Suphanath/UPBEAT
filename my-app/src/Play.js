import React from "react";
import "./Play.css";
import Hexagon from "./components/Hexagon";
import { useState, useEffect } from "react";

const Play = () => {
  const board = [];
  // generate rows
  for (let i = 0; i < 8; i++) {
    const row = [];
    // generate columns
    for (let j = 0; j < 9; j++) {
      row.push(
        <div>
          <Hexagon />
        </div>
      );
    }
    board.push(
      <div>
        <Hexagon />
      </div>
    );
  }

  const containerStyle = {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  };

  const boardContainerStyle = {
    flex: 1,
  };

  const textContainerStyle = {
    marginRight: "150px",
  };

  const headingStyle = {
    textAlign: "center",
    fontFamily: "Geostar Fill",
    fontSize: "4rem",
    color: "#FFE600",
    fontSize: "50px",
    margin: 100,
  };

  const buttonStyle = {
    display: "block",
    margin: "0 auto",
    marginBottom: "50px",
    fontSize: "25px",
  };

  const textAreaStyle = {
    width: "100%",
    height: "500px",
    marginBottom: 20,
  };

  const [textInput, setTextInput] = useState("");
  const [currentPlayer, setCurrentPlayer] = useState("Player 1");
  const [countdownTime, setCountdownTime] = useState(120);
  const [player1CountdownTime, setPlayer1CountdownTime] = useState(120);
  const [player2CountdownTime, setPlayer2CountdownTime] = useState(120);

  // Code to handle the submission of the text goes here
  const handleSubmit = (event) => {
    event.preventDefault();

    setTextInput("");
    // Change the current player
    setCurrentPlayer(currentPlayer === "Player 1" ? "Player 2" : "Player 1");

    // Remember the previous countdown time for the current player
    const previousCountdownTime =
      currentPlayer === "Player 1" ? countdownTime : player2CountdownTime;
    if (previousCountdownTime > 0) {
      if (currentPlayer === "Player 1") {
        setPlayer2CountdownTime(previousCountdownTime);
      } else {
        setPlayer1CountdownTime(previousCountdownTime);
      }
    }

    // Reset the countdown time for the current player
    setCountdownTime(
      currentPlayer === "Player 1" ? player1CountdownTime : player2CountdownTime
    );
  };

  // Countdown timer
  useEffect(() => {
    let timer;

    if (countdownTime > 0) {
      timer = setTimeout(() => {
        setCountdownTime((prevTime) => prevTime - 1);
      }, 1000);
    }

    return () => clearTimeout(timer);
  }, [countdownTime]);

  const minutes = Math.floor(countdownTime / 60);
  const seconds = countdownTime % 60;

  return (
    <div>
      <div style={containerStyle}>
        <div style={boardContainerStyle}>{board}</div>
        <div style={textContainerStyle}>
          <div style={{ fontSize: "32px", color: "white" }}>
            {currentPlayer} 's turn
          </div>
          <div style={{ fontSize: "28px", color: "white" }}>
            {currentPlayer === "Player 1" && (
              <>
                <div>
                  Player 1 time remaining: {minutes}:{seconds < 10 ? "0" : ""}
                  {seconds} minutes
                </div>
                <br />
              </>
            )}
            {currentPlayer === "Player 2" && (
              <>
                <div>
                  Player 2 time remaining: {minutes}:{seconds < 10 ? "0" : ""}
                  {seconds} minutes
                </div>
                <br />
              </>
            )}
          </div>
          <br></br>
          <label>
            <textarea
              type="text"
              value={textInput}
              onChange={(e) => setTextInput(e.target.value)}
              style={textAreaStyle}
            />
          </label>
          <button style={buttonStyle} onClick={handleSubmit}>
            Submit
          </button>
          <a href="/">Go to main (for dev)</a>
        </div>
      </div>
    </div>
  );
};

const link = document.createElement("link");
link.href =
  "https://fonts.googleapis.com/css2?family=Geostar+Fill&display=swap";
link.rel = "stylesheet";
document.head.appendChild(link);

export default Play;

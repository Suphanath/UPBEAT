import React from "react";
import HexagonGrid from "./hexagongrid.js";
import times from "lodash/times";

const HexGridDemo = () => {
  const getHexProps = (hexagon) => {
    return {
      style: {
        fill: "#0f1626",
        stroke: "#ab987a"
      },
      onClick: () => alert(`Hexagon ${hexagon+1} has been clicked`)
    };
  };

  const renderHexagonContent = (hexagon) => {
    return (
      <text
        x="50%"
        y="50%"
        fontSize={100}
        fontWeight="lighter"
        style={{ fill: "#ab987a" }}
        textAnchor="middle"
      >
        {hexagon+1}
      </text>
    );
  };

  let hexagons = times(108, (id) => id);

  return (
    <HexagonGrid
      gridWidth={1000}
      gridHeight={1000}
      hexagons={hexagons}
      hexProps={getHexProps}
      renderHexagonContent={renderHexagonContent}
    />
  );
};

export default HexGridDemo;

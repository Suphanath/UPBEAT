import React from "react";
import './hexagon.css';

export type CellProps = {
  x: number;
  y: number;
  color: string;
  paint: (x: number, y: number) => void;
};

const Cell = ({ x, y, color, paint }: CellProps) => {
  return (
    <div className="honeycomb"
      draggable='true'
      onClick={() => paint(x, y)}
      onDragEnter={() => paint(x, y)}
    > <div>
        <div className="ibws-fix">
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
          <div className="hexagon">
          </div>
        </div>
      </div>
    </div>
  );
};

export default React.memo(Cell);

package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Piece {
    public final PieceName name;
    public final PieceTeam team;
    public final int power;
    public final int movementRange;

    public final Cell cell;
    public boolean isAlive;


    public Piece(PieceName name, PieceTeam team, int power,int movementRange,Cell cell) {
        this.name = name;
        this.team = team;
        this.cell = cell;
        this.power = power;
        this.movementRange = movementRange;
        this.isAlive = true;
    }

    @Override
    public String toString() {
        return "<Piece:"+name+","+team+",row:"+cell.row+",column:"+cell.column+">";
    }

    void getValidMoves(){
        throw new UnsupportedOperationException(getClass().getName()+
                ".getValidMoves must be overridden by subclasses");
    }
    void moveTo(){
        throw new UnsupportedOperationException(getClass().getName()+
                ".moveTo must be overridden by subclasses");
    }
    void moveToTakePiece(){
        throw new UnsupportedOperationException(getClass().getName()+
                ".moveToTakePiece must be overridden by subclasses");
    }


}

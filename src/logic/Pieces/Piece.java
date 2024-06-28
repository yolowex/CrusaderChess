package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import jdk.jshell.spi.ExecutionControl;

public class Piece {
    public final PieceName name;
    public final PieceTeam team;
    public int row;
    public int column;
    public boolean isAlive;

    public Piece(PieceName name, PieceTeam team, int row, int column) {
        this.name = name;
        this.team = team;
        this.row = row;
        this.column = column;
        this.isAlive = true;
    }

    @Override
    public String toString() {
        return "<Piece:"+name+","+team+",row:"+row+",column:"+column+">";
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

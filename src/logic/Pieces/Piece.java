package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

import java.util.ArrayList;
import java.util.Objects;

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

    public ArrayList<Cell> getValidMoves(){
        ArrayList<Cell> validMoves = new ArrayList<>();

        for (int i = 0; i < movementRange; i++) {
            validMoves.add(cell.getTopCell(i+1));
            validMoves.add(cell.getBottomCell(i+1));
            validMoves.add(cell.getRightCell(i+1));
            validMoves.add(cell.getLeftCell(i+1));
            //
            validMoves.add(cell.getTopRightCell(i+1));
            validMoves.add(cell.getTopLeftCell(i+1));
            validMoves.add(cell.getBottomRightCell(i+1));
            validMoves.add(cell.getBottomLeftCell(i+1));
        }
        validMoves.removeIf(Objects::isNull);
        validMoves.removeIf(cell_ -> cell_.collidesWithAllies(this));
        return  validMoves;
    }

    public void moveTo(int row,int column){
        cell.update(row, column);

        // this way of accessing Game is probably bad
        cell.board.game.turnMove();

    }

    public void moveToTakePiece(Piece piece){
        cell.update(piece.cell.row, piece.cell.column);
        piece.isAlive = false;
        cell.board.game.turnMove();

    }


}

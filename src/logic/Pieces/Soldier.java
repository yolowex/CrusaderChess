package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

import java.util.ArrayList;
import java.util.Objects;

public class Soldier extends Piece{
    public Soldier(PieceTeam team, Cell cell) {

        super(PieceName.SOLDIER, team,1,1, cell);
    }

    @Override
    public ArrayList<Cell> getValidMoves() {
        ArrayList<Cell> validMoves = new ArrayList<>();
        if (team == PieceTeam.CRUSADERS_WHITE){
            validMoves.add(cell.getTopCell(1));
            validMoves.add(cell.getTopRightCell(1));
            validMoves.add(cell.getTopLeftCell(1));
        }
        else{
            validMoves.add(cell.getBottomCell(1));
            validMoves.add(cell.getBottomRightCell(1));
            validMoves.add(cell.getBottomLeftCell(1));
        }
        validMoves.removeIf(Objects::isNull);
        validMoves.removeIf(this::collidesWithAllies);
        validMoves.removeIf(this::isWeakerThanPieceAt);
        return  validMoves;
    }

    @Override
    protected int getPowerInfluence(Piece otherPiece) {
        if (otherPiece.team == team && otherPiece.name == PieceName.SOLDIER){
            return 1;
        }
        return 0;
    }
}

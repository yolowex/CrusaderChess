package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

import java.util.ArrayList;

public class Stronghold extends Piece{
    public Stronghold(PieceTeam team, Cell cell) {
        super(
                PieceName.STRONGHOLD,
                team,
                1,
                1,
                cell
        );
    }

    @Override
    public ArrayList<Cell> getValidMoves() {
        ArrayList<Cell> validMoves = super.getValidMoves();
        validMoves.removeIf(cell_ -> cell_.collidesWithAnyPiece(this));
        return validMoves;
    }
}

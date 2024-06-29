package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;

public class Stronghold extends Piece{
    public Stronghold(PieceTeam team, int row, int column) {
        super(PieceName.STRONGHOLD, team, row, column);
    }
}

package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;

public class Archer extends Piece{
    public Archer(PieceTeam team, int row, int column) {
        super(PieceName.ARCHER, team, row, column);
    }
}

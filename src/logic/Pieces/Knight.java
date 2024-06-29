package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;

public class Knight extends Piece{
    public Knight(PieceTeam team, int row, int column) {
        super(PieceName.KNIGHT, team, row, column);
    }
}

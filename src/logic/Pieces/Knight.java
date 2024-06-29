package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Knight extends Piece{
    public Knight(PieceTeam team, Cell cell) {
        super(PieceName.KNIGHT, team,1,2, cell);
    }
}

package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;

public class Soldier extends Piece{
    public Soldier(PieceTeam team, int row, int column) {
        super(PieceName.SOLDIER, team, row, column);
    }
}

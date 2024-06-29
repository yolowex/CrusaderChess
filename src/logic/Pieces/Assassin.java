package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;

public class Assassin extends Piece{
    public Assassin(PieceTeam team, int row, int column) {
        super(PieceName.ASSASSIN, team, row, column);
    }
}

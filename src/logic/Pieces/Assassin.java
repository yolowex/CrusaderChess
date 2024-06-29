package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Assassin extends Piece{
    public Assassin(PieceTeam team, Cell cell) {
        super(PieceName.ASSASSIN, team,0,1, cell);
    }
}

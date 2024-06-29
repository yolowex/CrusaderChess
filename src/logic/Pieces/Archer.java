package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Archer extends Piece{
    public Archer(PieceTeam team, Cell cell) {
        super(PieceName.ARCHER, team,2,1, cell);
    }
}

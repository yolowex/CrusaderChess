package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Stronghold extends Piece{
    public Stronghold(PieceTeam team, Cell cell) {
        super(PieceName.STRONGHOLD, team,1,1, cell);
    }
}

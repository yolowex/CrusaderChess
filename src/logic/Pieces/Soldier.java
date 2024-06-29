package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

public class Soldier extends Piece{
    public Soldier(PieceTeam team, Cell cell) {

        super(PieceName.SOLDIER, team,1,1, cell);
    }
}

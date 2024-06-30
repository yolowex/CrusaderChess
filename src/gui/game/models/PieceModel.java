package gui.game.models;

import common.enums.PieceData;
import common.enums.PieceName;
import common.enums.PieceTeam;

public class PieceModel {
    public final PieceName pieceName;
    public final PieceData pieceData;
    public final PieceTeam pieceTeam;
    public boolean isToggled;
    public int row;
    public int column;
    public int power;

    public PieceModel(PieceName pieceName,PieceData pieceData,PieceTeam pieceTeam, int power, int row,
                      int column) {
        this.pieceName = pieceName;
        this.pieceData = pieceData;
        this.pieceTeam = pieceTeam;
        this.power = power;
        this.row = row;
        this.column = column;
        this.isToggled = false;
    }

    @Override
    public String toString() {
        return "<Piece: "+pieceName+", row: "+row+" column: "+column+">";
    }

    public void toggle(){
        isToggled = !isToggled;
    }
}

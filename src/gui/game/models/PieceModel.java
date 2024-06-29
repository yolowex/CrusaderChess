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
    public boolean isAlive;

    public PieceModel(PieceName pieceName,PieceData pieceData,PieceTeam pieceTeam, int row, int column) {
        this.pieceName = pieceName;
        this.pieceData = pieceData;
        this.pieceTeam = pieceTeam;
        this.row = row;
        this.column = column;
        this.isToggled = false;
        this.isAlive = true;
    }

    public void toggle(){
        isToggled = !isToggled;
    }
}

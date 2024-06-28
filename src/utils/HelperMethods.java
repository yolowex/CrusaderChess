package utils;

import common.enums.PieceData;
import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import java.awt.*;

public class HelperMethods {

    public static Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static PieceData getPieceDataByPiece(Piece piece){
        if (piece.team == PieceTeam.WHITE){
            if (piece.name == PieceName.SOLDIER){
                return PieceData.WHITE_SOLDIER;
            }
        }
        else{
            if (piece.name == PieceName.SOLDIER){
                return PieceData.BLACK_SOLDIER;
            }
        }
        throw new Error("Could not find appropriate PieceData for piece "+piece);
    }

}

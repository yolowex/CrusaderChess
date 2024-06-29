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
        // this is probably bad practice

        if (piece.team == PieceTeam.CRUSADERS_WHITE){
            if (piece.name == PieceName.SOLDIER)    {return PieceData.WHITE_SOLDIER;}
            if (piece.name == PieceName.KNIGHT)     {return PieceData.WHITE_KNIGHT;}
            if (piece.name == PieceName.ARCHER)     {return PieceData.WHITE_ARCHER;}
            if (piece.name == PieceName.STRONGHOLD) {return PieceData.WHITE_STRONGHOLD;}
            if (piece.name == PieceName.ASSASSIN)    {return PieceData.WHITE_ASSASSIN;}
        }
        else{
            if (piece.name == PieceName.SOLDIER)    {return PieceData.BLACK_SOLDIER;}
            if (piece.name == PieceName.KNIGHT)     {return PieceData.BLACK_KNIGHT;}
            if (piece.name == PieceName.ARCHER)     {return PieceData.BLACK_ARCHER;}
            if (piece.name == PieceName.STRONGHOLD) {return PieceData.BLACK_STRONGHOLD;}
            if (piece.name == PieceName.ASSASSIN)    {return PieceData.BLACK_ASSASSIN;}
        }
        throw new Error("Could not find appropriate PieceData for piece "+piece);
    }

}

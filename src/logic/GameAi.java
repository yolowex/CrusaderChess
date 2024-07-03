package logic;

import common.enums.GameMode;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class GameAi extends Game{
    public GameAi(GameMode gameMode) {
        super(gameMode);
    }

    public void makeAiMove(){
        if (getCurrentPlayerTurn() == PieceTeam.CRUSADERS_WHITE) return;

        System.out.println("making ai move");

        ArrayList<Piece.PieceMoveBundle> validMoves = board.getValidAIPieceMoveBundles();

        Random random = new Random();
        validMoves.get(random.nextInt(validMoves.size())).performMove();

    }

}

package logic;

import common.enums.GameMode;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import java.util.List;

public class Game {
    private Board board;
    private GameMode gameMode;
    private PieceTeam currentPlayerTurn;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board();
        this.currentPlayerTurn = PieceTeam.CRUSADERS_WHITE;
    }

    public List<Piece> getPiecesList(){
        return board.getPiecesList();
    }
    public PieceTeam getCurrentPlayerTurn(){return currentPlayerTurn;}
}

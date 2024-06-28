package logic;

import common.enums.GameMode;
import logic.Pieces.Piece;

import java.util.List;

public class Game {
    private Board board;
    private GameMode gameMode;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board();
    }

    public List<Piece> getPiecesList(){
        return board.getPiecesList();
    }
}

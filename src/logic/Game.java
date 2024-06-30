package logic;

import common.enums.GameMode;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private GameMode gameMode;
    private PieceTeam currentPlayerTurn;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board(this);
        this.currentPlayerTurn = PieceTeam.CRUSADERS_WHITE;
    }


    public Piece findPieceAt(int row,int column){
        return board.findPieceAt(row,column);
    }
    public List<Piece> getPiecesList(){
        return board.getPiecesList();
    }
    public PieceTeam getCurrentPlayerTurn(){return currentPlayerTurn;}

    public void turnMove(){
        if (currentPlayerTurn == PieceTeam.CRUSADERS_WHITE){
            currentPlayerTurn = PieceTeam.MUSLIMS_BLACK;
        }
        else{
            currentPlayerTurn = PieceTeam.CRUSADERS_WHITE;
        }
    }
}

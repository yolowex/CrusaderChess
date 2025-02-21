package logic;

import common.Constants;
import common.enums.GameMode;
import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    protected Board board;
    protected GameMode gameMode;
    protected PieceTeam currentPlayerTurn;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board(this);
        this.currentPlayerTurn = PieceTeam.CRUSADERS_WHITE;
    }

    public String getGameState(){
        Piece whiteStronghold = null;
        Piece blackStronghold = null;

        for (Piece piece: board.getPiecesList()){
            if (piece.team == PieceTeam.CRUSADERS_WHITE && piece.name == PieceName.STRONGHOLD){
                whiteStronghold = piece;
            }
            if (piece.team == PieceTeam.MUSLIMS_BLACK && piece.name == PieceName.STRONGHOLD){
                blackStronghold = piece;
            }
        }

        if (whiteStronghold == null || blackStronghold == null){
            throw new Error("One of the kings is missing!");
        }

        if (!whiteStronghold.isAlive){
            return Constants.blackWinText;
        }
        if (!blackStronghold.isAlive){
            return Constants.whiteWinText;
        }

        return Constants.ongoingGameText;
    }

    public Board getBoard() {
        return board;
    }

    public Piece findPieceAt(int row, int column){
        return board.findPieceAt(row,column);
    }

    public List<Piece> getPiecesList(){
        return board.getPiecesList();
    }

    public void setPiecesList(ArrayList<Piece> piecesList){
        board.setPiecesList(piecesList);
    }
    public PieceTeam getCurrentPlayerTurn(){return currentPlayerTurn;}

    public void setCurrentPlayerTurn(PieceTeam currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public void turnMove(){


        if (currentPlayerTurn == PieceTeam.CRUSADERS_WHITE){
            currentPlayerTurn = PieceTeam.MUSLIMS_BLACK;
        }
        else{
            currentPlayerTurn = PieceTeam.CRUSADERS_WHITE;
        }

    }
}

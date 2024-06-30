package logic;

import common.enums.PieceTeam;
import logic.Pieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    final private ArrayList<Piece> piecesList = new ArrayList<>();
    final public Game game;
    final public int boardTotalColumns; // x
    final public int boardTotalRows; // y

    public List<Piece> getPiecesList() {
        // return an unmodifiable version to prevent random fuck-ups
        return Collections.unmodifiableList(piecesList);
    }

    public Board(Game game){
        this.game = game;
        this.boardTotalColumns = 4;
        this.boardTotalRows = 8;
        initializePieces();
    }

    public Piece findPieceAt(int row,int column){
        for (Piece piece: piecesList) {
            if (!piece.isAlive) continue;
            if (piece.cell.row == row && piece.cell.column == column){
                return piece;
            }
        }
        throw new Error("Could not find Piece with row "+row+" and column "+column);
    }

    private void initializePieces(){
        //
        piecesList.add(new Archer(PieceTeam.MUSLIMS_BLACK,new Cell(0,0,this)));
        piecesList.add(new Stronghold(PieceTeam.MUSLIMS_BLACK,new Cell(0,1,this)));
        piecesList.add(new Assassin(PieceTeam.MUSLIMS_BLACK,new Cell(0,2,this)));
        piecesList.add(new Knight(PieceTeam.MUSLIMS_BLACK,new Cell(0,3,this)));
        //
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,new Cell(1,0,this)));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,new Cell(1,1,this)));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,new Cell(1,2,this)));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,new Cell(1,3,this)));
        //
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,new Cell(6,0,this)));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,new Cell(6,1,this)));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,new Cell(6,2,this)));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,new Cell(6,3,this)));
        //
        piecesList.add(new Knight(PieceTeam.CRUSADERS_WHITE,new Cell(7,0,this)));
        piecesList.add(new Assassin(PieceTeam.CRUSADERS_WHITE,new Cell(7,1,this)));
        piecesList.add(new Stronghold(PieceTeam.CRUSADERS_WHITE,new Cell(7,2,this)));
        piecesList.add(new Archer(PieceTeam.CRUSADERS_WHITE,new Cell(7,3,this)));
        //
    }

}

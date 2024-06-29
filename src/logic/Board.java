package logic;

import common.enums.PieceTeam;
import logic.Pieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    final  private ArrayList<Piece> piecesList = new ArrayList<>();

    public List<Piece> getPiecesList() {
        // return an unmodifiable version to prevent random fuck-ups
        return Collections.unmodifiableList(piecesList);
    }

    public Board(){
        initializePieces();
    }

    void initializePieces(){
        //
        piecesList.add(new Archer(PieceTeam.MUSLIMS_BLACK,0,0));
        piecesList.add(new Stronghold(PieceTeam.MUSLIMS_BLACK,0,1));
        piecesList.add(new Assassin(PieceTeam.MUSLIMS_BLACK,0,2));
        piecesList.add(new Knight(PieceTeam.MUSLIMS_BLACK,0,3));
        //
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,1,0));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,1,1));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,1,2));
        piecesList.add(new Soldier(PieceTeam.MUSLIMS_BLACK,1,3));
        //
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,6,0));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,6,1));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,6,2));
        piecesList.add(new Soldier(PieceTeam.CRUSADERS_WHITE,6,3));
        //
        piecesList.add(new Knight(PieceTeam.CRUSADERS_WHITE,7,0));
        piecesList.add(new Assassin(PieceTeam.CRUSADERS_WHITE,7,1));
        piecesList.add(new Stronghold(PieceTeam.CRUSADERS_WHITE,7,2));
        piecesList.add(new Archer(PieceTeam.CRUSADERS_WHITE,7,3));
        //
    }

}

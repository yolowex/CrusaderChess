package logic.Pieces;

import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Cell;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    public final PieceName name;
    public final PieceTeam team;
    public final int power;
    public final int movementRange;

    public final Cell cell;
    public boolean isAlive;


    public Piece(PieceName name, PieceTeam team, int power,int movementRange,Cell cell) {
        this.name = name;
        this.team = team;
        this.cell = cell;
        this.power = power;
        this.movementRange = movementRange;
        this.isAlive = true;
    }

    @Override
    public String toString() {
        return "<Piece:"+name+","+team+",row:"+cell.row+",column:"+cell.column+">";
    }

    public ArrayList<Cell> getValidMoves(){
        ArrayList<Cell> validMoves = new ArrayList<>();

        for (int i = 0; i < movementRange; i++) {
            validMoves.add(cell.getTopCell(i+1));
            validMoves.add(cell.getBottomCell(i+1));
            validMoves.add(cell.getRightCell(i+1));
            validMoves.add(cell.getLeftCell(i+1));
            //
            validMoves.add(cell.getTopRightCell(i+1));
            validMoves.add(cell.getTopLeftCell(i+1));
            validMoves.add(cell.getBottomRightCell(i+1));
            validMoves.add(cell.getBottomLeftCell(i+1));
        }
        validMoves.removeIf(Objects::isNull);
        validMoves.removeIf(this::collidesWithAllies);
        validMoves.removeIf(this::isWeakerThanPieceAt);
        return  validMoves;
    }




    public void moveTo(int row,int column){
        cell.update(row, column);

        // this way of accessing Game is probably bad
        cell.board.game.turnMove();

    }

    public void moveToTakePiece(Piece piece){
        cell.update(piece.cell.row, piece.cell.column);
        piece.isAlive = false;
        cell.board.game.turnMove();

    }

    protected int getPowerInfluence(Piece otherPiece){
        // we change the values of this on pieces that influence the power of other pieces,
        // in the descendant classes
        return 0;
    }

    private ArrayList<Piece> getAdjacentPieces(){
        ArrayList<Cell> adjacentCells = cell.getAdjacentCells();
        ArrayList<Piece> adjacentPieces = new ArrayList<>();

        for (Cell cell: adjacentCells){
            try{
                Piece piece = cell.board.game.findPieceAt(cell.row,cell.column);
                if (piece != null){
                    adjacentPieces.add(piece);
                }
            }
            catch (FindException e){
                continue;
            }
        }

        return adjacentPieces;
    }

    public int getCurrentPower(){

        int finalPower = power;

        for (Piece piece: getAdjacentPieces()){
            finalPower += piece.getPowerInfluence(this);
        }

        return finalPower;
    }


    // used for move validation
    public boolean collidesWithAnyPiece(Cell p_cell){
        for (Piece piece_: p_cell.board.getPiecesList()) {
            if (!piece_.isAlive) continue;
            if (piece_.cell.row == p_cell.row && piece_.cell.column == p_cell.column){
                return true;
            }
        }
        return false;
    }

    // used for move validation
    public boolean collidesWithAllies(Cell p_cell){
        for (Piece piece_: p_cell.board.getPiecesList()) {
            if (!piece_.isAlive) continue;
            if (piece_.cell.row == p_cell.row && piece_.cell.column == p_cell.column){
                if (team == piece_.team){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isWeakerThanPieceAt(Cell p_cell){
        // checks if there is a piece in the target cell,
        // and if there is, checks if the power of that piece is greater than
        // this peace
        try{
            Piece piece = cell.board.game.findPieceAt(p_cell.row,p_cell.column);
            if (this.getCurrentPower() < piece.getCurrentPower()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (FindException e){
            return false;
        }
    }

}

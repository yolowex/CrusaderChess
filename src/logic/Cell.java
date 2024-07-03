package logic;

import logic.Pieces.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cell implements Serializable {
    public int row;
    public int column;
    public Board board;

    public Cell(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    @Override
    public String toString() {
        return "<Cell: row:"+row+", column:"+column+">";
    }

    public void update(Cell cell){
        this.row = cell.row;
        this.column = cell.column;
    }

    public void update(int row,int column){
        this.row = row;
        this.column = column;
    }


    private boolean isInBoardBounds(){
        // todo: check validity
        if (this.row < 0) return false;
        if (this.column < 0) return false;
        if (this.row > (board.boardTotalRows - 1)) return false;
        if (this.column > (board.boardTotalColumns - 1)) return false;

        return true;
    }

    private Cell getModifiedCell(int rowOffset,int columnOffset,int step){
        Cell cell = new Cell(row+(rowOffset*step),column+(columnOffset*step),board);
        if (cell.isInBoardBounds()){
            return cell;
        }
        return null;
    }

    public ArrayList<Cell> getAdjacentCells(){
        ArrayList<Cell> adjacentCells = new ArrayList<>();

        adjacentCells.add(getTopCell(1));
        adjacentCells.add(getBottomCell(1));
        adjacentCells.add(getRightCell(1));
        adjacentCells.add(getLeftCell(1));
        //
        adjacentCells.add(getTopRightCell(1));
        adjacentCells.add(getTopLeftCell(1));
        adjacentCells.add(getBottomRightCell(1));
        adjacentCells.add(getBottomLeftCell(1));
        //

        adjacentCells.removeIf(Objects::isNull);
        return adjacentCells;
    }

    public Cell getTopCell(int step){return  getModifiedCell(-1,0,step);}
    public Cell getBottomCell(int step){return getModifiedCell(1,0,step);}
    public Cell getRightCell(int step){return  getModifiedCell(0,1,step);}
    public Cell getLeftCell(int step){return getModifiedCell(0,-1,step);}
    public Cell getTopRightCell(int step){return  getModifiedCell(-1,1,step);}
    public Cell getTopLeftCell(int step){return  getModifiedCell(-1,-1,step);}
    public Cell getBottomRightCell(int step){return getModifiedCell(1,1,step);}
    public Cell getBottomLeftCell(int step){return getModifiedCell(1,-1,step);}

}

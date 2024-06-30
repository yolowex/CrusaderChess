package logic;

import logic.Pieces.Piece;

public class Cell {
    public int row;
    public int column;
    public final Board board;

    public Cell(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    public void update(Cell cell){
        this.row = cell.row;
        this.column = cell.column;
    }

    public void update(int row,int column){
        this.row = row;
        this.column = column;
    }

    // used for move validation
    public boolean collidesWithAnyPiece(Piece thisPiece){
        for (Piece piece_: board.getPiecesList()) {
            if (!piece_.isAlive) continue;
            if (piece_.cell.row == row && piece_.cell.column == column){
                return true;
            }
        }
        return false;
    }

    // used for move validation
    public boolean collidesWithAllies(Piece thisPiece){
        for (Piece piece_: board.getPiecesList()) {
            if (!piece_.isAlive) continue;
            if (piece_.cell.row == row && piece_.cell.column == column){
                if (thisPiece.team == piece_.team){
                    return true;
                }
            }
        }

        return false;
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

    public Cell getTopCell(int step){return  getModifiedCell(-1,0,step);}
    public Cell getBottomCell(int step){return getModifiedCell(1,0,step);}
    public Cell getRightCell(int step){return  getModifiedCell(0,1,step);}
    public Cell getLeftCell(int step){return getModifiedCell(0,-1,step);}
    public Cell getTopRightCell(int step){return  getModifiedCell(-1,1,step);}
    public Cell getTopLeftCell(int step){return  getModifiedCell(-1,-1,step);}
    public Cell getBottomRightCell(int step){return getModifiedCell(1,1,step);}
    public Cell getBottomLeftCell(int step){return getModifiedCell(1,-1,step);}

}

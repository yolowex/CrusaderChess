package logic;

public class Cell {
    public int row;
    public int column;
    private final Board board;

    public Cell(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    public void update(Cell cell){
        this.row = cell.row;
        this.column = cell.column;
    }

    private boolean isInBoardBounds(){
        if (this.row < 0) return false;
        if (this.column < 0) return false;
        if (this.row > (board.boardTotalRows - 1)) return false;
        if (this.column > (board.boardTotalColumns - 1)) return false;

        return true;
    }

    private Cell getModifiedCell(int rowOffset,int columnOffset){
        Cell cell = new Cell(row+rowOffset,column+columnOffset,board);
        if (cell.isInBoardBounds()){
            return cell;
        }
        return null;
    }

    public Cell getTopCell(){return  getModifiedCell(-1,0);}
    public Cell getBottomCell(){return getModifiedCell(1,0);}
    public Cell getRightCell(){return  getModifiedCell(0,1);}
    public Cell getLeftCell(){return getModifiedCell(0,-1);}
    public Cell getTopRightCell(){return  getModifiedCell(-1,1);}
    public Cell getTopLeftCell(){return  getModifiedCell(-1,-1);}
    public Cell getBottomRightCell(){return getModifiedCell(1,1);}
    public Cell getBottomLeftCell(){return getModifiedCell(1,-1);}

}

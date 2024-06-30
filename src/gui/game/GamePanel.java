package gui.game;

import common.Constants;
import common.enums.GameMode;
import gui.events.BoardMouseListener;
import gui.game.components.CellHighlightComponent;
import gui.game.components.PieceComponent;
import gui.game.models.PieceModel;
import logic.Cell;
import logic.Game;
import logic.Pieces.Piece;
import utils.BoardCell;
import utils.HelperMethods;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public int parentFrameWidth;
    public int parentFrameHeight;
    public ArrayList<BoardCell> boardCells = new ArrayList<>();
    public ArrayList<BoardCell> toggledBoardCells = new ArrayList<>();
    public ArrayList<PieceModel> pieceModels = new ArrayList<>();
    public PieceModel toggledPiece = null;
    public Game game;

    public GamePanel(int parentFrameWidth, int parentFrameHeight) {
        this.game = new Game(GameMode.NORMAL);
        this.parentFrameWidth = parentFrameWidth;
        this.parentFrameHeight = parentFrameHeight;
        this.addMouseListener(new BoardMouseListener(this));
        initializeBoardCells();
        updatePieces();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        drawBoardCells(g);
        drawToggledCells(g);
        drawBoardPieces(g);
    }

    public void update(){
        updatePieces();
        toggledBoardCells.clear();
        if (toggledPiece != null){
            toggledBoardCells.addAll(
                    findValidMovesOfToggledPiece()
            );
        }
    }


    // update all info about pieces
    private void updatePieces(){
        pieceModels.clear();
        for (Piece piece: game.getPiecesList()){
            if (!piece.isAlive) continue;
            pieceModels.add(
                    new PieceModel(
                             piece.name
                            ,HelperMethods.getPieceDataByPiece(piece)
                            ,piece.team,piece.getCurrentPower()
                            ,piece.cell.row,
                             piece.cell.column
                    )
            );
        }
    }

    // initializing the positioning and data of each board cell
    private void initializeBoardCells(){
        ArrayList<BoardCell> result = new ArrayList<>();

        int cellSize = (int)(parentFrameHeight / 8);
        int xOffset = ((parentFrameHeight / 2) - (cellSize * 4)) / 2;
        boolean isWhite = true;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 4; x++) {
                Color color = Constants.boardCellWhite;
                if (! isWhite){
                    color = Constants.boardCellBlack;
                }
                result.add(
                        new BoardCell(xOffset + (x*cellSize),
                                y*cellSize,cellSize,cellSize,
                                y,x,color,Color.white
                        )
                );
                isWhite = ! isWhite;
            }
            isWhite = ! isWhite;
        }
        boardCells.clear();
        boardCells.addAll(result);
    }




    private void drawBoardCells(Graphics g){
        for (BoardCell cell: boardCells) {
            g.setColor(cell.backgroundColor);
            g.fillRect(cell.rectangle.x, cell.rectangle.y,
                    cell.rectangle.width, cell.rectangle.height
            );
            g.setColor(cell.borderColor);
            g.drawRect(cell.rectangle.x, cell.rectangle.y,
                    cell.rectangle.width, cell.rectangle.height
            );
        }
    }

    private void drawBoardPieces(Graphics g){
        for (PieceModel piece: pieceModels){
            BoardCell cell = findCellWithCoord(piece.row,piece.column);
            new PieceComponent(cell,piece).draw(g,this);
        }
    }

    private void drawToggledCells(Graphics g){
        for (BoardCell cell: toggledBoardCells) {
            new CellHighlightComponent(cell,Color.BLUE).draw(g);
        }
    }

    public void untoggleAllPieces(){
        for (PieceModel piece: pieceModels){
            piece.isToggled = false;
        }
        toggledPiece = null;
    }

    public PieceModel findPieceModelWithCell(BoardCell cell){
        for (PieceModel piece: pieceModels) {
            if (piece.row == cell.row && piece.column == cell.column){
                return  piece;
            }
        }

        return null;
    }

    public BoardCell findCellWithCoord(int row,int column) {
        // find a piece with its coordination
        for (BoardCell cell: boardCells) {
            if (cell.row == row && cell.column == column){
                return cell;
            }
        }
        // we don't return null because it could mess the game
        throw new Error("Could not find cell with row "+row+" and column "+column);
    }

    public Piece findPieceAt(int row, int column){
        // this could go bad, make sure the piece is there before calling
        return game.findPieceAt(row,column);
    }

    public Piece getToggledPiece(){
        return findPieceAt(toggledPiece.row, toggledPiece.column);
    }

    public ArrayList<BoardCell> findValidMovesOfToggledPiece(){
        ArrayList<BoardCell> validMoves = new ArrayList<>();

        for (Cell cell: getToggledPiece().getValidMoves()) {
            validMoves.add(
              findCellWithCoord(cell.row,cell.column)
            );
        }
        return validMoves;
    }


}

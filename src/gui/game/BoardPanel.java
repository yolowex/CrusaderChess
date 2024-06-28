package gui.game;

import common.Constants;
import common.enums.PieceTeam;
import gui.events.BoardMouseListener;
import gui.game.models.PieceModel;
import utils.BoardCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static common.enums.PieceData.BLACK_SOLDIER;
import static common.enums.PieceData.WHITE_SOLDIER;

public class BoardPanel extends JPanel {
    public int parentFrameWidth;
    public int parentFrameHeight;
    public ArrayList<BoardCell> boardCells = new ArrayList<>();
    public ArrayList<PieceModel> pieces = new ArrayList<>();
    public BoardPanel(int parentFrameWidth, int parentFrameHeight) {
        this.parentFrameWidth = parentFrameWidth;
        this.parentFrameHeight = parentFrameHeight;
        this.addMouseListener(new BoardMouseListener(this));
        initializeBoardCells();
        initializePieces();
    }
    // initializing the pieces
    void initializePieces(){
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,0,0));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,0,1));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,0,2));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,0,3));
        //
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,1,0));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,1,1));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,1,2));
        pieces.add(new PieceModel(BLACK_SOLDIER,PieceTeam.BLACK,1,3));
        //
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,6,0));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,6,1));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,6,2));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,6,3));
        //
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,7,0));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,7,1));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,7,2));
        pieces.add(new PieceModel(WHITE_SOLDIER,PieceTeam.WHITE,7,3));
        //
    }

    // initializing the positioning and data of each board cell
    void initializeBoardCells(){
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoardCells(g);
        drawBoardPieces(g);
    }


    void drawBoardCells(Graphics g){
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

    void drawBoardPieces(Graphics g){

        for (PieceModel piece: pieces){
            BoardCell cell = findCellWithCoord(piece.row,piece.column);

            g.drawImage(
                    piece.pieceData.image,
                    cell.rectangle.x,
                    cell.rectangle.y,
                    cell.rectangle.width,
                    cell.rectangle.height,
                    this
            );
        }


    }


    BoardCell findCellWithCoord(int row,int column) {
        // find a piece with its coordinations
        for (BoardCell cell: boardCells) {
            if (cell.row == row && cell.column == column){
                return cell;
            }
        }

        throw new Error("Could not find cell with row "+row+" and column "+column);
    }


}

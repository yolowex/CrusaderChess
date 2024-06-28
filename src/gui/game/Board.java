package gui.game;

import common.Constants;
import utils.BoardCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private int parentFrameWidth;
    private int parentFrameHeight;

    public Board(int parentFrameWidth, int parentFrameHeight) {
        this.parentFrameWidth = parentFrameWidth;
        this.parentFrameHeight = parentFrameHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoardCells(g);
    }


    void drawBoardCells(Graphics g){

        for (BoardCell cell: getBoardCells()) {
            g.setColor(cell.backgroundColor);
            g.fillRect(cell.x, cell.y, cell.width, cell.height);
            g.setColor(cell.borderColor);
            g.drawRect(cell.x, cell.y, cell.width, cell.height);
        }
    }


    ArrayList<BoardCell> getBoardCells(){
        ArrayList<BoardCell> result = new ArrayList<>();

        int cellSize = (int)(parentFrameHeight / 9);
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

        return result;
    }
}

package events.game;

import gui.game.BoardPanel;
import utils.BoardCell;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseListener extends MouseAdapter {
    public BoardPanel board;

    public BoardMouseListener(BoardPanel board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for (BoardCell cell: board.boardCells) {
            if (cell.rectangle.contains(new Point(x,y))){
                System.out.println("User clicked at cell " + cell.row + ","+cell.column);

            }
        }
    }
}

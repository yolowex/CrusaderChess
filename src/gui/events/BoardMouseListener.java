package gui.events;

import gui.game.GamePanel;
import utils.BoardCell;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseListener extends MouseAdapter {
    public GamePanel gamePanel;

    public BoardMouseListener(GamePanel board) {
        this.gamePanel = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for (BoardCell cell: gamePanel.boardCells) {
            if (cell.rectangle.contains(new Point(x,y))){
                System.out.println("User clicked at cell " + cell.row + ","+cell.column);



                gamePanel.repaint();
            }
        }
    }
}

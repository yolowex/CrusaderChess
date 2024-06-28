package events.game;

import gui.game.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseListener extends MouseAdapter {
    public Board board;

    public BoardMouseListener(Board board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
    }
}

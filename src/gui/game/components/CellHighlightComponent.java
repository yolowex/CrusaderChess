package gui.game.components;

import utils.BoardCell;

import java.awt.*;
import java.awt.image.ImageObserver;

public class CellHighlightComponent {
    // highlight a cell with a circle
    private final BoardCell cell;
    private final Color color;

    public CellHighlightComponent(BoardCell cell, Color color) {
        this.cell = cell;
        this.color = color;
    }

    public void draw(Graphics g){
        // draw a circle in this method
        Rectangle r = cell.rectangle;
        Rectangle shrinkedRect = new Rectangle(
                (int)(r.x + r.width * 0.25),
                (int)(r.y + r.height * 0.25),
                (int)(r.width * 0.5),
                (int)(r.height * 0.5)
        );
        g.setColor(color);
        g.fillOval(shrinkedRect.x,shrinkedRect.y,
                shrinkedRect.width,shrinkedRect.height);
    }
}

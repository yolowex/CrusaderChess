package utils;

import java.awt.*;

public class BoardCell {
    public Rectangle rectangle;

    public int row;
    public int column;

    public Color backgroundColor;
    public Color borderColor;

    public BoardCell(int x, int y, int width,
                     int height, int row, int column,
                     Color backgroundColor, Color borderColor
    ) {
        this.rectangle = new Rectangle(
                x,y,width,height
        );
        this.row = row;
        this.column = column;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
}

package utils;

import java.awt.*;

public class BoardCell {
    public int x;
    public int y;
    public int width;
    public int height;

    public int row;
    public int column;

    public Color backgroundColor;
    public Color borderColor;

    public BoardCell(int x, int y, int width,
                     int height, int row, int column,
                     Color backgroundColor, Color borderColor
    ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.row = row;
        this.column = column;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }
}

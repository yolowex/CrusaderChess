package gui.game.components;

import common.enums.PieceData;
import gui.game.models.PieceModel;
import utils.BoardCell;

import java.awt.*;
import java.awt.image.ImageObserver;

public class PieceComponent {
    final private BoardCell cell;
    final private PieceModel pieceModel;

    public PieceComponent(BoardCell cell, PieceModel pieceModel) {
        this.cell = cell;
        this.pieceModel = pieceModel;
    }

    public void draw(Graphics g,ImageObserver observer){
        if (pieceModel.isToggled){
            new CellHighlightComponent(cell,Color.red).draw(g);
        }

        g.drawImage(
                pieceModel.pieceData.image,
                cell.rectangle.x,
                cell.rectangle.y,
                cell.rectangle.width,
                cell.rectangle.height,
                observer
        );
    }


}

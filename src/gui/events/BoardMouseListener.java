package gui.events;

import gui.game.GamePanel;
import gui.game.models.PieceModel;
import utils.BoardCell;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouseListener extends MouseAdapter {
    public GamePanel gamePanel;

    public BoardMouseListener(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for (BoardCell cell: gamePanel.boardCells) {
            if (cell.rectangle.contains(new Point(x,y))){
                PieceModel clickedPiece = gamePanel.findPieceModelWithCell(cell);
                if (clickedPiece != null){
                    clickedOnPiece(clickedPiece);
                }
                else{
                    clickedOnEmptyCell(cell);
                }
                gamePanel.repaint();
            }
        }
    }

    private void clickedOnPiece(PieceModel piece){
        if (gamePanel.game.getCurrentPlayerTurn() == piece.pieceTeam)
        {
            gamePanel.untoggleAllPieces();
            piece.toggle();
            if (piece.isToggled) {
                gamePanel.toggledPiece = piece;
            }
        }
    }

    private void clickedOnEmptyCell(BoardCell cell){
    }
}

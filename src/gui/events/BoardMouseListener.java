package gui.events;

import gui.game.GamePanel;
import gui.game.models.PieceModel;
import logic.Pieces.Piece;
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
                    clickedOnPiece(clickedPiece,cell);
                }
                else{
                    clickedOnEmptyCell(cell);
                }
                gamePanel.update();
                gamePanel.repaint();
            }
        }
    }

    private void clickedOnPiece(PieceModel piece,BoardCell cell){
        if (gamePanel.game.getCurrentPlayerTurn() == piece.pieceTeam) {
            // if the piece the player clicked on is an ally, toggle that instead
            boolean wasToggled = piece.isToggled;
            gamePanel.untoggleAllPieces();
            if (!wasToggled){
                piece.toggle();
            }

            if (piece.isToggled) {
                gamePanel.toggledPiece = piece;
            }
        } else{
            // otherwise, try to take it the move is valid
            if (gamePanel.toggledPiece != null &&
                    gamePanel.toggledBoardCells.contains(cell)){

                Piece targetPiece = gamePanel.findPieceAt(cell.row,cell.column);
                gamePanel.getToggledPiece().moveToTakePiece(targetPiece);
                gamePanel.untoggleAllPieces();
            }
        }
    }

    private void clickedOnEmptyCell(BoardCell cell){
        if (gamePanel.toggledPiece != null &&
                gamePanel.toggledBoardCells.contains(cell)){

            gamePanel.getToggledPiece().moveTo(cell.row,cell.column);
            gamePanel.untoggleAllPieces();
        }

    }
}

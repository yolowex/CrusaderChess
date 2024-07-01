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


            if (gamePanel.toggledPiece != null){
                if (gamePanel.toggledPiece.row == piece.row
                        && gamePanel.toggledPiece.column == piece.column){
                    gamePanel.untoggleAllPieces();
                    return;
                }
            }

            PieceModel lastToggledPiece = gamePanel.toggledPiece;
            gamePanel.untoggleAllPieces();

            piece.toggle();
            if (lastToggledPiece == null || (lastToggledPiece.pieceName != piece.pieceName)
            ){

                if (Math.random() < 0.75){
                    // we don't want to always play the audio
                    // because the player could get a headache
                    gamePanel.stopAllAudios();
                    piece.pieceData.audioClip.setFramePosition(0);
                    piece.pieceData.audioClip.start();}
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

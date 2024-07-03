package gui.events;

import common.enums.GameMode;
import common.enums.PieceTeam;
import common.enums.SoundEffects;
import gui.app.AppSettings;
import gui.game.GamePanel;
import gui.game.models.PieceModel;
import logic.GameAi;
import logic.Pieces.Piece;
import socket.SocketManager;
import socket.SocketMessage;
import utils.BoardCell;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardMouseListener extends MouseAdapter {
    public GamePanel gamePanel;

    public BoardMouseListener(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // on these three condition blocks, prevent user from controlling opponent pieces
        if (gamePanel.gameMode == GameMode.PVP_SERVER
                && gamePanel.game.getCurrentPlayerTurn() == PieceTeam.MUSLIMS_BLACK){
            return;
        }

        if (gamePanel.gameMode == GameMode.PVP_CLIENT
                && gamePanel.game.getCurrentPlayerTurn() == PieceTeam.CRUSADERS_WHITE){
            return;
        }

        if (gamePanel.gameMode == GameMode.PLAYER_VS_AI
                && gamePanel.game.getCurrentPlayerTurn() == PieceTeam.MUSLIMS_BLACK){
            return;
        }

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
        AppSettings appSettings = AppSettings.getInstance();

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
                    // play this sound whenever is piece is selected
                    // we don't want to always play the audio
                    // because the player could get a headache
                    if (!appSettings.soundMuted){
                        gamePanel.stopAllAudios();
                        piece.pieceData.audioClip.setFramePosition(0);
                        piece.pieceData.audioClip.start();
                    }
                }
            }

            if (piece.isToggled) {
                gamePanel.toggledPiece = piece;
            }
        } else{
            // otherwise, try to take it the move is valid
            if (gamePanel.toggledPiece != null &&
                    gamePanel.toggledBoardCells.contains(cell)){

                Piece targetPiece = gamePanel.findPieceAt(cell.row,cell.column);
                if (!appSettings.soundMuted){
                    SoundEffects.TAKE_PIECE.audioClip.setFramePosition(0);
                    SoundEffects.TAKE_PIECE.audioClip.start();
                }
                gamePanel.getToggledPiece().moveToTakePiece(targetPiece);
                gamePanel.untoggleAllPieces();
                if (gamePanel.gameMode == GameMode.PLAYER_VS_AI){
                    ((GameAi) gamePanel.game).makeAiMove();
                }

                SocketMessage socketMessage = new SocketMessage(new ArrayList<>(gamePanel.game.getPiecesList()),
                        gamePanel.game.getCurrentPlayerTurn());

                if (gamePanel.gameMode == GameMode.PVP_SERVER){
                    SocketManager.getInstance().sendMessageToClient(socketMessage);
                }
                else if (gamePanel.gameMode == GameMode.PVP_CLIENT){
                    SocketManager.getInstance().sendMessageToServer(socketMessage);
                }
            }
        }
    }

    private void clickedOnEmptyCell(BoardCell cell){
//        System.out.println("User clicked on cell "+cell.row+" "+cell.column);
        AppSettings appSettings = AppSettings.getInstance();

        if (gamePanel.toggledPiece != null &&
                gamePanel.toggledBoardCells.contains(cell)){

            if (!appSettings.soundMuted){
                SoundEffects.MOVE_PIECE.audioClip.setFramePosition(0);
                SoundEffects.MOVE_PIECE.audioClip.start();
            }
            gamePanel.getToggledPiece().moveTo(cell.row,cell.column);
            gamePanel.untoggleAllPieces();
            if (gamePanel.gameMode == GameMode.PLAYER_VS_AI){
                ((GameAi) gamePanel.game).makeAiMove();
            }
            SocketMessage socketMessage = new SocketMessage(new ArrayList<>(gamePanel.game.getPiecesList()),
                    gamePanel.game.getCurrentPlayerTurn());

            if (gamePanel.gameMode == GameMode.PVP_SERVER){
                SocketManager.getInstance().sendMessageToClient(socketMessage);
            }
            else if (gamePanel.gameMode == GameMode.PVP_CLIENT){
                SocketManager.getInstance().sendMessageToServer(socketMessage);
            }


        }

    }
}

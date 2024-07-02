package socket;

import common.enums.PieceTeam;
import gui.game.GamePanel;
import logic.Board;
import logic.Game;
import logic.Pieces.Piece;

import java.io.Serializable;
import java.util.ArrayList;

public class SocketMessage implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serial version UID

    public ArrayList<Piece> pieces;
    public PieceTeam currentPlayerTurn;

    public SocketMessage(ArrayList<Piece> pieces, PieceTeam currentPlayerTurn) {
        this.pieces = pieces;
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public void sync(GamePanel gamePanel){
        for (Piece piece: pieces){
            piece.cell.board = gamePanel.game.getBoard();
        }

        gamePanel.game.setPiecesList(pieces);
        gamePanel.game.setCurrentPlayerTurn(currentPlayerTurn);
        gamePanel.update();
        gamePanel.repaint();
    }
}

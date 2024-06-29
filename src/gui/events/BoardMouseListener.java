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
//                System.out.println("User clicked at cell " + cell.row + ","+cell.column);
                PieceModel clickedPiece = gamePanel.findPieceModelWithCell(cell);
                if (clickedPiece != null){
//                    System.out.println(
//                            "User clicked at Piece "+clickedPiece.pieceName
//                            +" " + clickedPiece.row + ","+clickedPiece.column
//                    );
//

                    if (gamePanel.game.getCurrentPlayerTurn() == clickedPiece.pieceTeam)
                    {
                        gamePanel.untoggleAllPieces();
                        clickedPiece.toggle();
                        if (clickedPiece.isToggled) {
                            gamePanel.toggledPiece = clickedPiece;
                        }
                    }
                }

                gamePanel.repaint();
            }
        }
    }
}

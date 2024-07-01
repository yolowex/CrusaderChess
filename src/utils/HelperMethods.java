package utils;

import common.enums.PieceData;
import common.enums.PieceName;
import common.enums.PieceTeam;
import logic.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelperMethods {

    public static Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static PieceData getPieceDataByPiece(Piece piece){
        // this is probably bad practice

        if (piece.team == PieceTeam.CRUSADERS_WHITE){
            if (piece.name == PieceName.SOLDIER)    {return PieceData.WHITE_SOLDIER;}
            if (piece.name == PieceName.KNIGHT)     {return PieceData.WHITE_KNIGHT;}
            if (piece.name == PieceName.ARCHER)     {return PieceData.WHITE_ARCHER;}
            if (piece.name == PieceName.STRONGHOLD) {return PieceData.WHITE_STRONGHOLD;}
            if (piece.name == PieceName.ASSASSIN)    {return PieceData.WHITE_ASSASSIN;}
        }
        else{
            if (piece.name == PieceName.SOLDIER)    {return PieceData.BLACK_SOLDIER;}
            if (piece.name == PieceName.KNIGHT)     {return PieceData.BLACK_KNIGHT;}
            if (piece.name == PieceName.ARCHER)     {return PieceData.BLACK_ARCHER;}
            if (piece.name == PieceName.STRONGHOLD) {return PieceData.BLACK_STRONGHOLD;}
            if (piece.name == PieceName.ASSASSIN)    {return PieceData.BLACK_ASSASSIN;}
        }
        throw new Error("Could not find appropriate PieceData for piece "+piece);
    }


    public static void showAlert(String message) {

        JFrame alertFrame = new JFrame("Alert");

        alertFrame.setSize(300, 150);
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alertFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close Game");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertFrame.dispose();
                System.exit(0);
            }
        });

        // Add the button to the panel at the bottom
        panel.add(closeButton, BorderLayout.SOUTH);

        // Add the panel to the alert window
        alertFrame.add(panel);

        // Make the alert window visible
        alertFrame.setVisible(true);
    }
}

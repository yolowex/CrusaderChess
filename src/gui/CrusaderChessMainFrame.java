package gui;

import gui.game.GamePanel;
import gui.app.AppMenuBar;
import utils.HelperMethods;

import javax.swing.*;
import java.awt.*;

public class CrusaderChessMainFrame {
    private JFrame frame;
    private int frameWidth;
    private int frameHeight;

    public CrusaderChessMainFrame(){
        initializeFrame();
    }

    private void initializeFrame(){
        frame = new JFrame();
        frame.setTitle("Crusader Chess");

        Dimension scrSize = HelperMethods.getScreenSize();

        frameWidth = (int)(scrSize.height * 0.7 * 0.9);
        frameHeight = (int)(scrSize.height * 0.7);
        frame.setSize(frameWidth,frameHeight);//400 width and 500 height
        frame.setResizable(true);
        frame.setLayout(null);//using no layout managers
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        new AppMenuBar(frame);
        new GamePanel(frame,frameWidth,(int) (frameHeight * 0.92));

        frame.setVisible(true);//making the frame visible
    }
}

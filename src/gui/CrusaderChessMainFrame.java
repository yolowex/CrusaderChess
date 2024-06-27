package gui;

import gui.app.AppMainPanel;
import gui.app.AppMenuBar;
import gui.game.Board;
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

        frameWidth = (int)(scrSize.height * 0.7 * 0.6);
        frameHeight = (int)(scrSize.height * 0.7);
        frame.setSize(frameWidth,frameHeight);//400 width and 500 height
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());//using no layout managers
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        new AppMenuBar(frame);
        new AppMainPanel(frame);

        frame.setVisible(true);//making the frame visible
    }
}

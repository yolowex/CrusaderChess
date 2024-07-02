package gui;

import gui.game.MainPanel;
import gui.app.AppMenuBar;
import utils.HelperMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        frameWidth = (int)(scrSize.height * 0.7 * 0.5);
        frameHeight = (int)(scrSize.height * 0.7);
        frame.setSize(frameWidth,frameHeight);//400 width and 500 height
        frame.setResizable(false);
        frame.setLayout(null);//using no layout managers
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Set to do nothing

        // Add window listener to handle window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        new AppMenuBar(frame);
        new MainPanel(frame, frameWidth, (int) (frameHeight * 0.92));

        frame.setVisible(true);//making the frame visible
    }

    private void onClose() {
        // Your custom close logic here
        System.exit(0);
        // Dispose the frame
        frame.dispose();
    }
}

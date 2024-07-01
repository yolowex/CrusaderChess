package gui.game;

import gui.app.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    private final JFrame parentFrame;
    private final int parentWidth;
    private final int parentHeight;
    private JPanel panel;

    public MainPanel(JFrame parentFrame, int parentWidth, int parentHeight){
        this.parentFrame = parentFrame;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;

        panel = new MainMenuPanel(parentWidth, parentHeight,this);
        panel.setBackground(Color.WHITE);

        parentFrame.add(panel);

        panel.setBounds(
                ((parentWidth) - (int)(parentHeight * 0.5)) / 2,
                0,
                (int)(parentHeight * 0.5),
                parentHeight
        );
    }

    public void startPracticeMode(){
        JPanel panel = new GamePanel(parentWidth,parentHeight);
        parentFrame.remove(this.panel);
        this.panel = panel;
        panel.setBackground(Color.WHITE);
        parentFrame.add(panel);

        panel.setBounds(
                ((parentWidth) - (int)(parentHeight * 0.5)) / 2,
                0,
                (int)(parentHeight * 0.5),
                parentHeight
        );

        parentFrame.revalidate();
        parentFrame.repaint();
    }
}

package gui.game;

import gui.app.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel {
    private JPanel panel;

    public MainPanel(JFrame parentFrame, int parentWidth, int parentHeight){
//        panel = new GamePanel(parentWidth,parentHeight);
        panel = new MainMenu(parentWidth,parentHeight);
//        panel.add(new Board());
        // Add components to mainPanel
//        panel.setLayout(new BorderLayout());
//        panel.add(new JLabel("Hello, World!"), BorderLayout.CENTER);

        panel.setBackground(Color.WHITE);
        panel.setBounds(
                ((parentWidth) - (int)(parentHeight * 0.5)) / 2,
                0,
                (int)(parentHeight * 0.5)
                ,parentHeight
        );
        parentFrame.add(panel);
    }

    public void removePanel(JFrame parentFrame) {
        parentFrame.remove(panel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}

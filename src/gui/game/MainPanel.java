package gui.game;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    private JPanel panel;

    public MainPanel(JFrame parentFrame, int parentWidth, int parentHeight){
        JPanel panel = new GamePanel(parentWidth,parentHeight);


//        panel.add(new Board());
        // Add components to mainPanel
//        panel.setLayout(new BorderLayout());
//        panel.add(new JLabel("Hello, World!"), BorderLayout.CENTER);

        panel.setBackground(Color.WHITE);
        panel.setBounds(
                ((parentWidth) - (int)(parentHeight * 0.5)) / 2,
                0,(int)(parentHeight * 0.5)
                ,parentHeight
        );
        parentFrame.add(panel);
    }
}

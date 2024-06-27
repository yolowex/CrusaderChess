package gui.app;

import javax.swing.*;
import java.awt.*;

public class AppMainPanel {
    private JPanel panel;

    public AppMainPanel(JFrame parentFrame){
        JPanel panel = new JPanel();

        // Add components to mainPanel
//        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Hello, World!"), BorderLayout.CENTER);
        panel.setBackground(Color.white);
        panel.setBounds(0,0,500,500);
        parentFrame.add(panel);
        System.out.println("hello");
    }
}

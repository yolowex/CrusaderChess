package gui.app;

import gui.game.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private JRadioButton practiceMode;
    private JRadioButton pvpMode;
    private JRadioButton pvcMode;
    private JButton startButton;
    private ButtonGroup group;
    private JLabel instructionLabel;
    private final MainPanel mainPanel;

    public MainMenuPanel(int width, int height, MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        instructionLabel = new JLabel("Choose game mode to play");
        practiceMode = new JRadioButton("Practice mode");
        pvpMode = new JRadioButton("Player vs Player mode");
        pvcMode = new JRadioButton("Player vs Computer mode");
        startButton = new JButton("Start Game");

        group = new ButtonGroup();
        group.add(practiceMode);
        group.add(pvpMode);
        group.add(pvcMode);

        add(instructionLabel, gbc);
        add(practiceMode, gbc);
        add(pvpMode, gbc);
        add(pvcMode, gbc);
        add(startButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (practiceMode.isSelected()){
                    mainPanel.startPracticeMode();
                }
                if (pvpMode.isSelected()) {
                    removeAll();
                    add(new JLabel("Choose an option:"), gbc);
                    JButton createServerButton = new JButton("Create Server");
                    JButton joinServerButton = new JButton("Join Server");
                    JButton pvpStartButton = new JButton("Start");

                    add(createServerButton, gbc);
                    add(joinServerButton, gbc);
                    add(pvpStartButton, gbc);
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
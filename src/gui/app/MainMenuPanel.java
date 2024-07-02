package gui.app;

import gui.game.MainPanel;
import utils.SocketManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                    startPVPMenu(gbc);
                }
            }
        });
    }

    private void startPVPMenu(GridBagConstraints gbc){
        removeAll();
        add(new JLabel("Choose an option:"), gbc);
        JButton createServerButton = new JButton("Create Server");
        JButton joinServerButton = new JButton("Join Server");

        add(createServerButton, gbc);
        add(joinServerButton, gbc);


        // for server
        createServerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(new JLabel("Waiting for opponent to connect..."), gbc);
                revalidate();
                repaint();
                // when i try to repaint the panel here, the app stucks
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        startServer(gbc);
                    }
                }).start();
            }
        });

        // for client
        joinServerButton.addActionListener(e -> joinServer(gbc));

        revalidate();
        repaint();
    }

    private void startServer(GridBagConstraints gbc) {

        SocketManager socketManager = SocketManager.getInstance();
        try {

            socketManager.startServer(12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void joinServer(GridBagConstraints gbc) {
        SocketManager socketManager = SocketManager.getInstance();
        try {
            socketManager.startClient("localhost",12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
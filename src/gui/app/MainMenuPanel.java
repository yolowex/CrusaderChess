package gui.app;

import gui.game.MainPanel;

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


        createServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer(gbc);
            }
        });

        joinServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinServer(gbc);
            }
        });

        revalidate();
        repaint();
    }

    private void startServer(GridBagConstraints gbc) {
        removeAll();
        add(new JLabel("Waiting for opponent to connect..."), gbc);
        revalidate();
        repaint();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(12345);
                    System.out.println("Server started. Waiting for a client...");



                    removeAll();
                    add(new JLabel("Connected to Client!!"), gbc);
                    revalidate();
                    repaint();
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket.getInetAddress());
                    new Thread(new ClientHandler(socket)).start();
                    serverSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void joinServer(GridBagConstraints gbc) {
        try {
            Socket socket = new Socket("localhost", 12345); // Replace "localhost" with server IP if needed
            System.out.println("Connected to server: " + socket.getInetAddress());

            // Get output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            // Send a message to the server
            String message = "Hello from client!";
            out.println(message);

            // Close resources
            out.close();
            outputStream.close();

            removeAll();
            add(new JLabel("Connected to Server!!"), gbc);
            revalidate();
            repaint();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Set up input stream to receive data from client
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received from client: " + message);

                    // Handle the received message here as needed
                }

                // Close resources
                reader.close();
                inputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
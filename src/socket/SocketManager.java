package socket;

import gui.game.GamePanel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager {
    private static SocketManager instance;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private GamePanel gamePanel = null;

    private SocketManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started, listening on port " + port);

        clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        sendToClient("HElllloooo Im server!");

        new Thread(this::listenToClient).start();
    }

    public void startClient(String serverAddress, int port) throws IOException {
        clientSocket = new Socket(serverAddress, port);
        System.out.println("Connected to server: " + serverAddress + ":" + port);

        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        sendToServer("HElllloooo Im client!");

        new Thread(this::listenToServer).start();
    }

    public void sendToClient(String message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(String message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToClient(SocketMessage message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToServer(SocketMessage message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToClient() {
        try {
            while (true) {
                Object obj = objectInputStream.readObject();
                if (obj instanceof String) {
                    System.out.println("Received from client: " + obj);
                } else if (obj instanceof SocketMessage) {
                    System.out.println("Received SocketMessage from client: "+
                            ((SocketMessage) obj).currentPlayerTurn );

                    ((SocketMessage) obj).sync(gamePanel);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer() {
        try {
            while (true) {
                Object obj = objectInputStream.readObject();
                if (obj instanceof String) {
                    System.out.println("Received from server: " + obj);
                } else if (obj instanceof SocketMessage) {
                    System.out.println("Received SocketMessage from server: "+
                            ((SocketMessage) obj).currentPlayerTurn );
                    ((SocketMessage) obj).sync(gamePanel);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    public void stopClient() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
    }

    public void registerGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
}
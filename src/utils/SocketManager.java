package utils;

import gui.game.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager {
    private static SocketManager instance;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientReader;
    private PrintWriter clientWriter;
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
        clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        sendToClient("HElllloooo Im server!");

        new Thread(this::listenToClient).start();
    }

    public void startClient(String serverAddress, int port) throws IOException {
        clientSocket = new Socket(serverAddress, port);
        System.out.println("Connected to server: " + serverAddress + ":" + port);

        clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        sendToClient("HElllloooo Im client!");

        new Thread(() -> listenToServer()).start();
    }

    public void sendToClient(String message) {
        clientWriter.println(message);
    }

    public void sendToServer(String message) {
        clientWriter.println(message);
    }

    private void listenToClient() {
        String message;
        try {
            while ((message = clientReader.readLine()) != null) {
                System.out.println("Received from client: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer() {
        String message;
        try {
            while ((message = clientReader.readLine()) != null) {
                System.out.println("Received from server: " + message);
            }
        } catch (IOException e) {
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
package server.test.multithreading_clent_server_connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5060)) {
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                SocketHandler socketHandler = new SocketHandler(socket);
                socketHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

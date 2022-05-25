package server.test.multitreading_clent_server_connection;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketHandler extends Thread {
    private Socket socket;


    public SocketHandler(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            System.out.println("Установлено новое соединение с удаленным адресом" + socketAddress.toString());
            Connection connection = new Connection(socket);
            while (true) {
                String message = connection.receive();
                if (message.contains("Bye")) {
                    break;
                }
                System.out.println("Получено сообщение сервером: " + message);
                connection.send(message + "\r\n");
                System.out.println("Отправлено сообщение сервером:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

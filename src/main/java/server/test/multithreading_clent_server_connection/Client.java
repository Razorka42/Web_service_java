package server.test.multithreading_clent_server_connection;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    protected Connection connection;

    public Client(String name) {
        super(name);

    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Client client = new Client("" + i);
            client.start();
        }
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 5050);
            connection = new Connection(socket);
            System.out.println("Client " + this.getName() + "started");
            for (int i = 0; i < 5; i++) {
                connection.send("Hello from" + this.getName() + "\r\n");
                System.out.println("Send Hello from client" + this.getName());
                String mes = connection.receive();
                System.out.println("Received message from server to client" + this.getName());
            }
            connection.send("Bye\r\n");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

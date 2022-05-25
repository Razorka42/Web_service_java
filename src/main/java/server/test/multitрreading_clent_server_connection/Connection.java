package server.test.multitрreading_clent_server_connection;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class Connection implements Closeable {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        InputStream socketIn = socket.getInputStream();
        OutputStream socketOut = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(socketIn));
        writer = new BufferedWriter(new OutputStreamWriter(socketOut));

    }

    public void send(String message) throws IOException {
        synchronized (writer) {
            writer.write(message);
            writer.flush();
        }
    }

    public String receive() throws IOException {
        synchronized (reader) {
            String message = reader.readLine();
            return message;
        }
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();

    }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private OutputStream outputStream;
    private final Set<ClientHandler> clients;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.clientSocket = socket;
        this.clients = clients;
        try {
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            System.err.println("Error creating output stream: " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all clients.
     * This method override run() method in Thread class.
     * When you call start() on an instance of your Thread subclass, it internally calls the run()
     * method you've overridden.`
     *
     */
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received: " + message);

                // Broadcast the message to all clients
                synchronized (clients) {
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            try {
                                client.outputStream.write(message.getBytes());
                            } catch (IOException e) {
                                System.err.println("Error broadcasting message: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                clients.remove(this);
                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}

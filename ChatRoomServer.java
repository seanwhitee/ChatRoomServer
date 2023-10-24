import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatRoomServer {
    private int portNumber;
    private Set<ClientHandler> clients;

    public ChatRoomServer(int portNumber) {
        this.portNumber = portNumber;
        this.clients = new HashSet<>();
    }

    public void startServerAction() {
        try (ServerSocket serverSocket = new ServerSocket(this.portNumber)) {
            System.out.println("Chat Room Server is listening on port " + this.portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.err.println("Error creating the server socket: " + e.getMessage());
        }
    }
}

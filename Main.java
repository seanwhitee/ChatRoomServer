import java.util.Scanner;

/**
 * The Main class is responsible for starting the chatroom and accepting user
 * input for the port number.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Specify the port number for " client connection ".
        System.out.printf("Input the port number: ");
        int port = scanner.nextInt();

        // Create a new chatroom server
        ChatRoomServer server = new ChatRoomServer(port);

        server.startServerAction();

        // Close scanner object
        scanner.close();
    }
}

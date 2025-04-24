
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            while (true) {
                System.out.println("Waiting for client connection...");

                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected: " + socket.getPort());

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    String input = in.readLine();
                    System.out.println("Received: " + input);

                    Model model = new Model();
                    
                    out.println(model.getResults());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

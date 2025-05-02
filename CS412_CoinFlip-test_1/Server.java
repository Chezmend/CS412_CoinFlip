import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<myServerThread> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("Server started. Listening on port 5001...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress() + ":" + socket.getPort());
                
                myServerThread clientThread = new myServerThread(socket, clients);
                synchronized (clients) {
                    clients.add(clientThread);    
                }
                new Thread(clientThread).start();
            }

        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}

import java.io.*;
import java.net.Socket;
import java.util.List;

public class myServerThread implements Runnable {
    private final Socket socket;
    private final List<myServerThread> clients;

    public myServerThread(Socket socket, List<myServerThread> clients) {
        this.socket = socket;
        this.clients = clients;
        synchronized (clients) {
            clients.add(this); // Add this client to the list
        }
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;
            Model model = new Model();

            while ((input = in.readLine()) != null) {
                System.out.println("Received from " + socket.getPort() + ": " + input);
                String[] ParseInput = input.split(" ");

                if (ParseInput.length != 4) {
                    out.println("ERROR WRONG INPUT");
                    continue;
                }

                if (input.contains("USERNAME:")) {
                    model.setUser(ParseInput[3], ParseInput[1], 500);
                    out.println("username created");
                } else if (input.contains("CHOICE:")) {
                    try {
                        int amount = Integer.parseInt(ParseInput[3]);
                        out.println(model.getResults(amount, ParseInput[1]));
                    } catch (NumberFormatException e) {
                        out.println("ERROR INVALID AMOUNT");
                    }
                } else if (input.contains("GUESS:")) {
                    try {
                        int amount = Integer.parseInt(ParseInput[3]);
                        out.println(model.getDice(amount, ParseInput[1]));
                    } catch (NumberFormatException e) {
                        out.println("ERROR INVALID AMOUNT");
                    }
                } else if (input.contains("LOGIN:")) {
                    if (model.verifying(ParseInput[3], ParseInput[1])) {
                        out.println("LOGGED IN");
                    } else {
                        out.println("FAILED LOGGED IN");
                    }
                } else if (input.contains("LOGOUT:")) {
                    out.println("LOGGED OUT");
                    break; // Exit loop and close in finally
                } else {
                    out.println("leaderboard");
                }
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }

            // Remove this thread from the clients list
            synchronized (clients) {
                clients.remove(this);
            }

            System.out.println("Disconnected client: " + socket.getInetAddress() + ":" + socket.getPort());
        }
    }
}

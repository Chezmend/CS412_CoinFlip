import java.io.*;
import java.net.Socket;
import java.util.List;

public class myServerThread implements Runnable {
    private final Socket socket;
    private final List<myServerThread> clients;
    private final Model model;  
    private String Username;
    
    public myServerThread(Socket socket, List<myServerThread> clients) {
        this.socket = socket;
        this.clients = clients;
        this.model = new Model();
        synchronized (clients) {
            clients.add(this); 
        }
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;

            while ((input = in.readLine()) != null) {
                System.out.println("Received from " + socket.getPort() + ": " + input);
                String[] ParseInput = input.split(" ");
                System.out.println("Raw input: " + input + "'");

                if (ParseInput.length != 4) {
                    if (input.contains("LEADERBOARD:")) {
                        out.println(model.getLeaderBoard());
                        continue;
                    }
                    out.println("ERROR WRONG INPUT");
                    continue;
                }

                if (input.contains("USERNAME:")) {
                    model.setUser(ParseInput[3], ParseInput[1], 500);
                    out.println("username created");
                } else if (input.contains("CHOICE:")) {
                    try {
                        int amount = Integer.parseInt(ParseInput[3]);
                        out.println(model.getResults(amount, ParseInput[1],Username));
                    } catch (NumberFormatException e) {
                        out.println("ERROR INVALID AMOUNT");
                    }
                } else if (input.contains("GUESS:")) {
                    try {
                        int amount = Integer.parseInt(ParseInput[3]);
                        out.println(model.getDice(amount, ParseInput[1],Username));
                    } catch (NumberFormatException e) {
                        out.println("ERROR INVALID AMOUNT");
                    }
                } else if (input.contains("LOGIN:")) {
                    //System.out.println("Username: " + ParseInput[1] + " Password: " + ParseInput[3]);
                    this.Username = ParseInput[1];
                    if (model.verifying(ParseInput[1],ParseInput[3])) {
                        out.println("LOGGED IN");
                    } else {
                        out.println("FAILED LOGGED IN");
                    }
                } else if (input.contains("LOGOUT:")) {
                    out.println("LOGGED OUT");
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

            synchronized (clients) {
                clients.remove(this);
            }

            System.out.println("Disconnected client: " + socket.getInetAddress() + ":" + socket.getPort());
        }
    }
}

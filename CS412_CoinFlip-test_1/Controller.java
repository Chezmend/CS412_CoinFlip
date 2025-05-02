import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    private View view;

    private create c;
    private Flip f;
    private Roll r;
    private login l;
    private Logout lo;
    private leaderBoard lb;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {
        View v = new View();
        Controller c = new Controller(v);
        v.initializeUI();  // optionally move this inside Controller after listeners
    }

    public Controller(View view) {
        this.view = view;
        l = view.getLoginPanel();
        c = view.getCreatePanel();
        f = view.getFlipPanel();
        r = view.getRollPanel();
        lo = view.getLogoutPanel();
        lb = view.getLeaderBoard();

        try {
            socket = new Socket("localhost", 5001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
            e.printStackTrace();
        }

        c.addActionListnerButton(new createTab());
        f.addActionListnerButton(new FlipTab());
        r.addActionListnerButton(new RollTab());
        l.addActionListnerButton(new loginTab());
        lo.addActionListnerButton(new logoutTab());
    }

    public class RollTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Roll();
        }
    }

    public class FlipTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Flip();
        }
    }

    public class logoutTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }

    public class loginTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            login();
        }
    }

    public class createTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            create();
        }
    }

    public void Flip() {
        String input = "CHOICE: " + f.getChoice() + " AMOUNT: " + f.getAmount();
        try {
            out.println(input);
            String response = in.readLine();
            f.setResult(response);
            leaderBoard();
        } catch (IOException e) {
            System.out.println("Error during Flip: " + e.getMessage());
        }
    }

    public void Roll() {
        String input = "GUESS: " + r.getGuess() + " AMOUNT: " + r.getAmount();
        try {
            out.println(input);
            String response = in.readLine();
            r.setResult(response);
            leaderBoard();
        } catch (IOException e) {
            System.out.println("Error during Roll: " + e.getMessage());
        }
    }

    public void create() {
        String input = "USERNAME: " + c.getUsername() + " Password: " + c.getPassword();
        try {
            out.println(input);
            String response = in.readLine();
            //c.setResult(response);
        } catch (IOException e) {
            System.out.println("Error during Create: " + e.getMessage());
        }
    }

    public void login() {
        String input = "LOGIN: " + l.getUsername() + " Password: " + l.getPassword();
        try {
            out.println(input);
            String response = in.readLine();
            System.out.println(response);

            if ("LOGGED IN".equals(response)) {
                view.LOGGEDIN();
                leaderBoard();
            }
        } catch (IOException e) {
            System.out.println("Error during Login: " + e.getMessage());
        }
    }

    public void logout() {
        System.out.println("GOT HERE");
        String input = "LOGOUT: " + l.getUsername() + " Password: " + l.getPassword();
        try {
            out.println(input);
            String response = in.readLine();
            view.LOGGEDOUT();
        } catch (IOException e) {
            System.out.println("Error during Logout: " + e.getMessage());
        }
    }

    public void leaderBoard() {
        String input = "LEADERBOARD:";
        try {
            out.println(input);
            String response = in.readLine();
            lb.setPlayers(response);
        } catch (IOException e) {
            System.out.println("Error during LeaderBoard: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error during Disconnect: " + e.getMessage());
        }
    }
}

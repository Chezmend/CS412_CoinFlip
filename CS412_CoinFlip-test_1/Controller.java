
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    public static void main(String[] args) {

        View v = new View();
        Controller c = new Controller(v);

        v.initializeUI();
    }

    private View view;
    private create c;
    private Flip f;
    private Roll r;
    private login l;
    private Logout lo;

    public Controller(View view) {
        this.view = view;
        l = view.getLoginPanel();
        c = view.getCreatePanel();
        f = view.getFlipPanel();
        r = view.getRollPanel();
        lo = view.getLogoutPanel();

        c.addActionListnerButton(new createTab());
        f.addActionListnerButton(new FlipTab());
        r.addActionListnerButton(new RollTab());
        l.addActionListnerButton(new loginTab());
        lo.addActionListnerButton(new logoutTab());
    }
    public class RollTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Roll();
        }
    }
    public class FlipTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Flip();
        }
    }
    public class logoutTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            logout();
        }
    }
    public class loginTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            login();
        }
    }
    public class createTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            create();
        }
    }
    public void Flip() {
        String input = "CHOICE: " + f.getChoice() + " AMOUNT: " + f.getAmount();
        try (Socket socket = new Socket("localhost", 5001)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();

            f.setResult(response);
            System.out.println(response);

        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }

    public void Roll() {
        System.out.println("GOT HERE");
        String input = "GUESS: " + r.getGuess() + " AMOUNT: " + r.getAmount();
        try (Socket socket = new Socket("localhost", 5001)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();

            r.setResult(response);
            System.out.println(response);

        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }

    public void create() {
        System.out.println("GOT HERE");
        String input = "USERNAME: " + c.getUsername() +  " Password: " + c.getPassword();

        try (Socket socket = new Socket("localhost", 5001))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();

        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }

    public void login()
    {
        System.out.println("GOT HERE");
        String input = "LOGIN: " + l.getUsername() +  " Password: " + l.getPassword();

        try (Socket socket = new Socket("localhost", 5001))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println(response);

            if(response.equals("LOGGED IN")){
                view.LOGGEDIN();
            }
        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }
    public void logout()
    {
        System.out.println("GOT HERE");
        String input = "LOGOUT: " + l.getUsername() +  " Password: " + l.getPassword();

        try (Socket socket = new Socket("localhost", 5001))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            view.LOGGEDOUT();
        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }

}

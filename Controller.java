
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {

    private View view;

    public Controller(View view) {
        this.view = view;
        login l = view.getLoginPanel();
        create c = view.getCreatePanel();
        c.addActionListnerButton(new createTab());
        l.addActionListnerButton(new loginTab());
    }

    public class loginTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }
    public class createTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }
    public void go() {
        System.out.println("GOT HERE");
        String input = view.getjtextareaClient().trim();
        try (Socket socket = new Socket("localhost", 5001)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("SENDING: " + input);
            out.println(input);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();

            view.setjtextareaServer(response);

        } catch (IOException e) {
            System.out.println("FAILED to connect to server");
        }
    }
}

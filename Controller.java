
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListnerButton(new ActionListenerButton());
    }

    public class ActionListenerButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    public void go() {
        String input = view.getjtextareaClient().trim();
        if (input.isEmpty()) {
            view.setjtextareaServer("ERROR: BAD INPUT");
            return;
        }

        boolean isParsed = model.parseInput(input);

        if (!isParsed) {
            view.setjtextareaServer("ERROR: BAD INPUT");
            return;
        }

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

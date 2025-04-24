
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class leaderBoard extends JPanel {

    private JTextField username;
    private JTextField password;
    private JPanel inputPanel;

    public leaderBoard() {
        username = new JTextField(10);
        password = new JTextField(10);

        inputPanel = new JPanel();
        inputPanel.add(username);
        inputPanel.add(password);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
    }

    public String getUsername() {
        return username.getText().trim();
    }

    public String getPassword() {
        return password.getText().trim();
    }

}

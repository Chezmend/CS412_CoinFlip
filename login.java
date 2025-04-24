
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JPanel {

    private JTextField username;
    private JTextField password;
    private JButton loginButton;
    private JPanel inputPanel;

    public login() {
        username = new JTextField(10);
        password = new JTextField(10);
        loginButton = new JButton("Login");

        inputPanel = new JPanel();
        inputPanel.add(username);
        inputPanel.add(password);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    public void addActionListnerButton(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public String getUsername() {
        return username.getText().trim();
    }

    public String getPassword() {
        return password.getText().trim();
    }

}


import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JPanel {

    private JTextField username;
    private JTextField password;
    private JButton loginButton;
    private JPanel inputPanel;
    private JLabel jl;
    private JLabel jl2;
    public login() {
        username = new JTextField(10);
        password = new JTextField(10);
        loginButton = new JButton("Login");
        jl = new JLabel("username: ");
        jl2 = new JLabel("username: ");

        inputPanel = new JPanel();
        inputPanel.add(username);
        inputPanel.add(password);
        inputPanel.add(jl2);
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(jl, BorderLayout.WEST);
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

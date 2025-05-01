
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Logout extends JPanel {

    private JButton loginButton;
    private JPanel inputPanel;

    public Logout() {

        loginButton = new JButton("Login");

        inputPanel = new JPanel();

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    public void addActionListnerButton(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

}


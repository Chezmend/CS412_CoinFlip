import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class create extends JPanel {
    private JTextField username;
    private JTextField password;
    private JButton CreateButton;
    private JPanel inputPanel;

    public create() {
        username = new JTextField(10);
        password = new JTextField(10);
        CreateButton = new JButton("create");

        inputPanel = new JPanel();
        inputPanel.add(username);
        inputPanel.add(password);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(CreateButton, BorderLayout.SOUTH);
    }
    public void addActionListnerButton(ActionListener listener) {
        CreateButton.addActionListener(listener);
    }
    public String getUsername() {
        return username.getText().trim();
    }
    
    public String getPassword() {
        return password.getText().trim();
    }
    
}

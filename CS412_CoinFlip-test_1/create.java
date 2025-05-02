import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class create extends JPanel {
    private JTextField username;
    private JTextField password;
    private JButton CreateButton;
    private JPanel inputPanel;
    private JLabel jLabelUser;
    private JLabel jLabelPassword;

    public create() {
        jLabelUser = new JLabel("Username");
        jLabelPassword = new JLabel("Password");
        username = new JTextField(10);
        password = new JTextField(10);
        CreateButton = new JButton("create");

        inputPanel = new JPanel();
        inputPanel.add(jLabelUser);
        inputPanel.add(username);
        inputPanel.add(jLabelPassword);
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

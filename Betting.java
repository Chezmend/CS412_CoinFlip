
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Betting extends JPanel {

    private JTextField Amount;
    private JTextField Result;
    private JButton SpinButton;
    private JPanel inputPanel;

    public Betting() {
        Amount = new JTextField(10);
        Result = new JTextField(10);

        SpinButton = new JButton("spin!");

        inputPanel = new JPanel();
        inputPanel.add(Amount);
        inputPanel.add(Result);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(SpinButton, BorderLayout.SOUTH);
    }

    public void addActionListnerButton(ActionListener listener) {
        SpinButton.addActionListener(listener);
    }

    public String getAmount() {
        return Amount.getText().trim();
    }

    public String getResult() {
        return Result.getText().trim();
    }

}

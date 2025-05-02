
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Roll extends JPanel {

    private JTextField Amount;
    private JTextField Result;
    private JTextField Guess;
    private JButton RollButton;
    private JPanel inputPanel;
    private JLabel jLabelGuess;
    private JLabel jLabelAmount;
    private JLabel jLabelRoll;


    public Roll() {
        jLabelGuess = new JLabel("please ROLL 1-6:");
        jLabelAmount = new JLabel("Enter in the Amount:");
        jLabelRoll = new JLabel("Actual Roll 1-6:");
        Amount = new JTextField(10);
        Result = new JTextField(10);
        Guess = new JTextField(10);
        RollButton = new JButton("ROLL!");

        inputPanel = new JPanel();
        inputPanel.add(jLabelGuess);
        inputPanel.add(Guess);
        inputPanel.add(jLabelAmount);
        inputPanel.add(Amount);
        inputPanel.add(jLabelRoll);
        inputPanel.add(Result);


        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(RollButton, BorderLayout.SOUTH);
    }

    public String getGuess(){
        System.out.println("choice: " + Guess.getText().trim());
        return Guess.getText().trim();
    }

    public void addActionListnerButton(ActionListener listener) {
        RollButton.addActionListener(listener);
    }

    public String getAmount() {
        return Amount.getText().trim();
    }

    public void setResult(String result) {
        Result.setText(result);
    }

}

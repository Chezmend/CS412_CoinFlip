
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Flip extends JPanel {

    private JTextField Amount;
    private JTextField Result;
    private JButton FlipButton;
    private JPanel inputPanel;
    private JList choice;
    private JLabel jLabelChoice;
    private JLabel jLabelAmount;
    private JLabel jLabelResult;


    public Flip() {
        jLabelAmount = new JLabel("Enter in the Amount:");
        jLabelChoice = new JLabel("choose heads or tails:");
        jLabelResult = new JLabel("actual result of coin flip:");

        Amount = new JTextField(10);
        Result = new JTextField(10);
        String options[]= { "heads","tails"};
        choice = new JList(options);

        FlipButton = new JButton("FLIP!");

        inputPanel = new JPanel();
        inputPanel.add(jLabelChoice);
        inputPanel.add(choice);
        inputPanel.add(jLabelAmount);
        inputPanel.add(Amount);
        inputPanel.add(jLabelResult);
        inputPanel.add(Result);


        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(FlipButton, BorderLayout.SOUTH);
    }
    public String getChoice(){
        System.out.println("choice: " + choice.getSelectedValue().toString());
        return choice.getSelectedValue().toString();
    }
    public void addActionListnerButton(ActionListener listener) {
        FlipButton.addActionListener(listener);
    }

    public String getAmount() {
        return Amount.getText().trim();
    }

    public void setResult(String result) {
        Result.setText(result);
    }

}

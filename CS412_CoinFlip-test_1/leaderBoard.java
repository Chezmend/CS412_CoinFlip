
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class leaderBoard extends JPanel {

    private JPanel inputPanel;
    private JTextArea JTA;

    public leaderBoard() {
        JTA = new JTextArea();
        inputPanel = new JPanel();
        inputPanel.add(JTA);
        setLayout(new BorderLayout());
        
        add(inputPanel, BorderLayout.CENTER);
    }
    public void setPlayers(String players) {
        JTA.setText(players);
    }
    
}

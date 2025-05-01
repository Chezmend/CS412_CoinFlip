
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class leaderBoard extends JPanel {

    private JPanel inputPanel;

    public leaderBoard() {

        inputPanel = new JPanel();

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
    }
    
}


import java.awt.BorderLayout;
import javax.swing.*;

public class View {

    private JTextArea jtextareaClient;
    private JTextArea jtextareaServer;

    private login l;
    private create c;
    private Betting b;
    private leaderBoard lb;

    private JTabbedPane jTabs;

    private JFrame jframe;
    private JPanel jpanel;

    private DefaultListModel listModel;
    private JList list1;

    public View() {

        listModel = new DefaultListModel();
        list1 = new JList(listModel);

        list1.setModel(listModel);


        jframe = new JFrame();

        jtextareaClient = new JTextArea(10, 10);
        jtextareaServer = new JTextArea(10, 10);

        l = new login();
        c = new create();
        b = new Betting();
        lb = new leaderBoard();

        jpanel = new JPanel();
        jTabs = new JTabbedPane();

    }

    public void setjtextareaServer(String compute) {
        jtextareaServer.setText(compute);
    }

    public String getjtextareaClient() {
        return jtextareaClient.getText();
    }

    public login getLoginPanel() {
        return l;
    }
    public create getCreatePanel() {
        return c;
    }
    public Betting getBetting(){
        return b;
    }
    public leaderBoard getLeaderBoard(){
        return lb;
    }
    public void initializeUI() {
        jTabs.add("Login", l);
        jTabs.add("create", c);
        jTabs.add("bet",b);
        jTabs.add("leaderboard",lb);

        jpanel.add(jtextareaClient, BorderLayout.SOUTH);
        jpanel.add(jtextareaServer);
        jpanel.add(list1);
        jframe.add(jpanel);
        jframe.add(jTabs);
        jframe.setSize(500, 600);
        jframe.setVisible(true);
    }
}

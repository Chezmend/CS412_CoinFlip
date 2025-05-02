
import javax.swing.*;

public class View {

    private login l;
    private create c;
    private Roll r;
    private Flip f;
    private leaderBoard lb;
    private Logout lo;
    private JTabbedPane jTabs;

    private JFrame jframe;
    private JPanel jpanel;

    public View() {

        jframe = new JFrame();

        l = new login();
        c = new create();
        r = new Roll();
        f = new Flip();
        lb = new leaderBoard();
        lo = new Logout();
        jpanel = new JPanel();
        jTabs = new JTabbedPane();

    }

    public login getLoginPanel() {
        return l;
    }

    public create getCreatePanel() {
        return c;
    }

    public Roll getRollPanel() {
        return r;
    }

    public Flip getFlipPanel() {
        return f;
    }

    public Logout getLogoutPanel() {
        return lo;
    }

    public leaderBoard getLeaderBoard() {
        return lb;
    }

    public void initializeUI() {
        jTabs.add("Login", l);
        jTabs.add("create", c);

        jframe.add(jpanel);
        jframe.add(jTabs);
        jframe.setSize(500, 600);
        jframe.setVisible(true);
    }

    public void LOGGEDIN() {
        jTabs.remove(0);
        jTabs.remove(0);

        jTabs.add("Roll", r);
        jTabs.add("flip", f);
        jTabs.add("leaderboard", lb);
        jTabs.add("logout", lo);
        jframe.add(jTabs);
        jframe.setSize(500, 600);
        jframe.setVisible(true);
    }

    public void LOGGEDOUT() {
        jTabs.remove(0);
        jTabs.remove(0);
        jTabs.remove(0);
        jTabs.remove(0);

        jTabs.add("login", l);
        jTabs.add("create", c);

        jframe.add(jTabs);
        jframe.setSize(500, 600);
        jframe.setVisible(true);
    }
}

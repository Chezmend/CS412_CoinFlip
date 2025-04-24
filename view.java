
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View {

    private JTextArea jtextareaClient;
    private JTextArea jtextareaServer;

    private JButton compute;
    private JFrame jframe;
    private JPanel jpanel;
    private Model model;

    private DefaultListModel listModel;
    private JList list1;

    public View(Model model) {
        this.model = model;

        listModel = new DefaultListModel();
        list1 = new JList(listModel);

        list1.setModel(listModel);

        compute = new JButton("add");

        jframe = new JFrame();

        jtextareaClient = new JTextArea(10, 10);
        jtextareaServer = new JTextArea(10, 10);

        jpanel = new JPanel();

    }

    public void addActionListnerButton(ActionListener listener) {
        compute.addActionListener(listener);
    }

    public void setjtextareaServer(String compute) {
        jtextareaServer.setText(compute);
    }

    public String getjtextareaClient() {
        return jtextareaClient.getText();
    }

    public void initializeUI() {
        jpanel.add(jtextareaClient, BorderLayout.SOUTH);
        jpanel.add(jtextareaServer);
        jpanel.add(list1);
        jframe.add(jpanel);
        jframe.add(compute, BorderLayout.NORTH);
        jframe.setSize(500, 600);
        jframe.setVisible(true);
    }
}

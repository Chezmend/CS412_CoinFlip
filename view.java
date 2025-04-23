import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class view {

    private JFrame jFrame;
    private JTabbedPane jTabs;
    private DefaultListModel<String> listModel;
    private JList<String> jlist;

    private JTextField tfn;
    private JTextField tfa;

    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;

    private model m;

    public view(model m) {
        this.m = m;
        listModel = new DefaultListModel<>();
        jlist = new JList<>(listModel);  // This JList will be shared across all tabs
        
        tfa = new JTextField(20);
        tfn = new JTextField(10);

        createButton = new JButton("CREATE");
        updateButton = new JButton("UPDATE");
        deleteButton = new JButton("DELETE");

        jFrame = new JFrame();
        jTabs = new JTabbedPane();
    }

    public void initializeUI() {
        jTabs.add("CREATE", makeCreateTab());
        jTabs.add("DELETE", makeDeleteTab());
        jTabs.add("READ", makeReadTab());
        jTabs.add("UPDATE", makeUpdateTab());

        updateList();


        jFrame.add(jTabs);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void updateList() {
        // Fetch the updated list of students from the model
        ArrayList<String> students = m.getStudents();
        listModel.clear();  // Clear the previous list

        // Add each student to the list model
        for (String student : students) {
            listModel.addElement(student);
        }
    }

    private JPanel makeUpdateTab() {
        JPanel jPanel = new JPanel();
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel inner = new JPanel();
        inner.setLayout(new GridLayout(2, 2));
        JTextField textFieldName = new JTextField(10);
        JTextField textFieldAge = new JTextField(10);
        inner.add(new JLabel("NAME:"));
        inner.add(textFieldName);
        inner.add(new JLabel("AGE:"));
        inner.add(textFieldAge);

        jPanel.add(jlist);  
        jPanel.add(inner);
        jPanel.add(updateButton);

        return jPanel;
    }

    private JPanel makeReadTab() {
        JPanel jPanel = new JPanel();
        jPanel.add(jlist); 

        return jPanel;
    }

    private JPanel makeDeleteTab() {
        JPanel jPanel = new JPanel();
        jPanel.add(jlist);  
        jPanel.add(deleteButton);
        return jPanel;
    }

    private JPanel makeCreateTab() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));
        jPanel.add(new JLabel("NAME:"));
        jPanel.add(tfn);
        jPanel.add(new JLabel("AGE:"));
        jPanel.add(tfa);
        jPanel.add(createButton);
        return jPanel;
    }

    public int getSelectedIndex() {
        return jlist.getSelectedIndex();
    }

    public String getSelectedValue() {
        return jlist.getSelectedValue();
    }

    public String getTextFieldName() {
        return tfn.getText().trim();
    }

    public String getTextFieldAge() {
        return tfa.getText().trim();
    }

    public int getSelectedStudentId() {
        String selected = jlist.getSelectedValue();
        if (selected != null && !selected.isEmpty()) {
            return Integer.parseInt(selected.trim().split("\\s+")[0]); // Extract ID
        }
        return -1; 
    }

    public void updateStudentList(ArrayList<String> students) {
        listModel.clear();  // Clear the previous list
        for (String student : students) {
            listModel.addElement(student);
        }
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
}

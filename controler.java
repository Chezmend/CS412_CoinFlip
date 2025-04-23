import java.awt.event.ActionListener;

public class controler {

    view v;
    model m;

    public controler(view v, model m) {
        this.v = v;
        this.m = m;
        v.setCreateButtonListener(new makeCreateTab());
        v.setDeleteButtonListener(new makeDeleteTab());
        v.setUpdateButtonListener(new makeUpdateTab());
    }

    public class makeDeleteTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = v.getSelectedIndex();
            String s = v.getSelectedValue();
            String[] words = s.trim().split(" ");
            int id = Integer.parseInt(words[0]);
            m.delete(id);
            v.updateList();  // Update the list after deletion
        }
    }

    public class makeCreateTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = v.getTextFieldName().trim();
            int age = Integer.parseInt(v.getTextFieldAge());
            m.create(name, age);
            v.updateList();  // Update the list after creation
        }
    }

    public class makeUpdateTab implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = v.getTextFieldName();
            int age = Integer.parseInt(v.getTextFieldAge());
            int id = v.getSelectedStudentId();
            m.update(id, name, age);
            v.updateList();  // Update the list after update
        }
    }
}

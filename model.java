import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class model {

    DatabaseConnection d;

    public model(DatabaseConnection d) {
        this.d = d;
    }

    public void create(String name, int age) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String cmd = "INSERT INTO students (name, age) VALUES (?, ?);";
            try (PreparedStatement cmdPre = conn.prepareStatement(cmd)) {
                cmdPre.setString(1, name);
                cmdPre.setInt(2, age);
                cmdPre.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(int id, String name, int age) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String cmd = "UPDATE students SET name = ?, age = ? WHERE id = ?";
            try (PreparedStatement cmdPre = conn.prepareStatement(cmd)) {
                cmdPre.setString(1, name);
                cmdPre.setInt(2, age);
                cmdPre.setInt(3, id);
                cmdPre.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String query = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement cmdPre = conn.prepareStatement(query)) {
                cmdPre.setInt(1, id);
                cmdPre.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Modified read() to return only name and age, no ID
    public String read(int id) {
        String studentInfo = "Student not found.";
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String query = "SELECT name, age FROM students WHERE id = ?";
            try (PreparedStatement cmdPre = conn.prepareStatement(query)) {
                cmdPre.setInt(1, id);
                try (ResultSet rs = cmdPre.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        studentInfo = name + " " + age;  // Only returning name and age
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return studentInfo;
    }

    // Modified getStudents() to return a list of name and age, no IDs
    public ArrayList<String> getStudents() {
        ArrayList<String> studentList = new ArrayList<>();
    
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT name, age FROM students");
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String studentData = name + " " + age; // Removed ID
                studentList.add(studentData);
                System.out.println("Fetched: " + studentData); // Debugging output
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total students retrieved: " + studentList.size()); // Debugging output
        return studentList;
    }
    
}

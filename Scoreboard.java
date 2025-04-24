
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Scoreboard {


    public Scoreboard() {
    }

    public String read(int id) {
        String studentInfo = "Student not found.";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String query = "SELECT Username, age FROM students WHERE id = ?";
            try (PreparedStatement cmdPre = conn.prepareStatement(query)) {
                cmdPre.setInt(1, id);
                try (ResultSet rs = cmdPre.executeQuery()) {
                    if (rs.next()) {
                        String Username = rs.getString("Username");
                        int age = rs.getInt("age");
                        studentInfo = id + " " + Username + " " + age;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return studentInfo;
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> PlayerList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db"); PreparedStatement pstmt = conn.prepareStatement("SELECT id, Username, age FROM students"); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String Username = rs.getString("Username");
                int Amount = rs.getInt("Amount");
                String PlayerData = id + " " + Username + " " + Amount;
                PlayerList.add(PlayerData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PlayerList;
    }
}

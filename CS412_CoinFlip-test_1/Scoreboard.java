
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Scoreboard {


    public Scoreboard() {
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> PlayerList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db"); PreparedStatement pstmt = conn.prepareStatement("SELECT id, Username, age FROM students"); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String Username = rs.getString("Username");
                int Amount = rs.getInt("Amount");
                String PlayerData = Username + " " + Amount;
                PlayerList.add(PlayerData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PlayerList;
    }
}

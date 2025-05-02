
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
    
        String sql = "SELECT players.Username, game.Amount " +
                     "FROM players " +
                     "JOIN game ON players.id = game.player_id " +
                     "ORDER BY game.Amount DESC " +
                     "LIMIT 3";
    
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                String Username = rs.getString("Username");
                int Amount = rs.getInt("Amount");
                System.out.println("SCOREBOARD: "+ Username + " " + Amount);
                String PlayerData = Username + " " + Amount;
                PlayerList.add(PlayerData);
            }
        } catch (SQLException e) {
            System.out.println("ERROR IN SCOREBOARD");
            e.printStackTrace();
        }
    
        return PlayerList;
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String username;
    private int Amount;
    private String password;

    public User(){

    }
    
    public void esstablish(String password, String username, int Amount){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String cmd = "INSERT INTO player (username, password, amount) VALUES (?, ?, ?)";
            try (PreparedStatement cmdPre = conn.prepareStatement(cmd)) {
                cmdPre.setString(1, username);
                cmdPre.setString(2, password);
                cmdPre.setInt(3, Amount);
                cmdPre.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

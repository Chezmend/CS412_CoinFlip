import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class User {
    private String username;

    public User() {

    }
    public String getUsername(){
        return username;
    }

    public void establish(String password, String username) {
        this.username = username;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {

            String insertPlayer = "INSERT INTO players (username, password) VALUES (?, ?)";
            try (PreparedStatement playerStmt = conn.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS)) {
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                System.out.println("Password hashed: " + hashed);

                playerStmt.setString(1, username);
                playerStmt.setString(2, hashed);
                playerStmt.executeUpdate();
                // resources for the fucntion call getGenerateKeys
                //https://stackoverflow.com/questions/63701435/getgeneratedkeys-method-how-does-it-work
                try (ResultSet rs = playerStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int playerId = rs.getInt(1);

                        String insertGame = "INSERT INTO game (player_id, amount) VALUES (?, ?)";
                        try (PreparedStatement gameStmt = conn.prepareStatement(insertGame)) {
                            gameStmt.setInt(1, playerId);
                            gameStmt.setInt(2, 500);
                            gameStmt.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean verifying(String password, String username) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String query = "SELECT password FROM players WHERE username = ? LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String hashed = rs.getString("password");
                        return BCrypt.checkpw(password, hashed);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

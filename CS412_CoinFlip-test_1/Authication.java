import java.sql.*;

public class Authication {
    private String username;
    private int Amount;
    private String password;

    public Authication(){

    }
    // https://codingtechroom.com/question/authenticate-user-java-database
    public void esstablish(String password, String username, int Amount){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String cmd = "INSERT INTO player (username, password, amount) VALUES (?, ?, ?)";
            try (PreparedStatement cmdPre = conn.prepareStatement(cmd)) {
                cmdPre.setString(1, username);
                cmdPre.setString(2, password);
                cmdPre.setInt(3, Amount);
                ResultSet rs = cmdPre.executeQuery(); // extract a specific column
                rs.next();
                cmdPre.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

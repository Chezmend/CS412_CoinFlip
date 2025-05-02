import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Game {

    public Game() {

    }
    public int getAmount(String username) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String query = "SELECT amount FROM game WHERE player_id = (SELECT id FROM players WHERE username = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("amount");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void update(String username,int amount) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String cmd = "UPDATE game SET amount = ? WHERE player_id = (SELECT id FROM players WHERE username = ?)";
            try (PreparedStatement cmdPre = conn.prepareStatement(cmd)) {
                cmdPre.setInt(1, amount);
                cmdPre.setString(2, username);
                cmdPre.executeUpdate();
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String Roll(int amount, String guess, String username){
        Random rand = new Random();
        int current = getAmount(username);

        int num = rand.nextInt(6);
        String results = Integer.toString(num);
        if(results.equals(guess)){
            current += amount;
            update(username, current);
        }
        else{
            current -= amount;
            update(username, current);
        }
        return results;
    }

    public String Flip(int amount, String guess, String username){
        Random rand = new Random();
        int num = rand.nextInt(2);
        int current = getAmount(username);

        if(num == 1){
            if(guess.equals("heads")){
                System.out.println("GOT HERE IN THE GAME: " + amount + " Username: " + username + "Guess: " + guess + " CURRENT: " + current);

                current += amount;
                update(username, current);
            }
            else{
                System.out.println("GOT HERE IN THE GAME: " + amount + " Username: " + username + "Guess: " + guess + " CURRENT: " + current);

                current -= amount;
                update(username, current);
            }
            return "HEADS";
        }
        else{
            if(guess.equals("tails")){
                current += amount;
                update(username, current);
            }
            else{
                current -= amount;
                update(username, current);
            }
            return "TAILS";
        }
    }
}

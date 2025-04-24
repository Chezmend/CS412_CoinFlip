import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Game {

    public Game() {

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

    public boolean Flip(){
        Random rand = new Random();
        int num = rand.nextInt(2);
        System.out.println(num);
        if(num == 1){
            return false;
        }
        else{
            return true;
        }
    }
}

import java.util.ArrayList;

public class Model {

    DatabaseConnection d;
    Game g;
    Scoreboard sb;
    User u;

    public Model(){
        DatabaseConnection d = new DatabaseConnection();
        this.d = d; 
        g = new Game();  
        u = new User();     
    }
 
    public void setUser(String username, String password, int Amount){
        u.establish(username,password);
    }

    public ArrayList<String> getLeaderBoard(){
        return sb.getPlayers();
    }

    public String getResults(int amount, String guess){
        return g.Flip(amount, guess, u.getUsername());
    }
    public String getDice(int amount, String guess){
        return g.Roll(amount, guess, u.getUsername());
    }
    public boolean verifying(String username, String password){
        return u.verifying(username,password);
    }
}

import java.util.ArrayList;

public class Model {

    private DatabaseConnection d;
    private Game g;
    private Scoreboard sb;
    private User u;
    private String username;
    private String loggedInUsername = null;

    public Model(){
        DatabaseConnection d = new DatabaseConnection();
        this.d = d;
        sb = new Scoreboard(); 
        g = new Game();  
        u = new User();     
    }
 
    public void setUser(String username, String password, int Amount){
        u.establish(username,password);
    }

    public ArrayList<String> getLeaderBoard(){
        return sb.getPlayers();
    }

    public String getResults(int amount, String guess, String username){
        return g.Flip(amount, guess, username);
    }
    public String getDice(int amount, String guess, String username){
        return g.Roll(amount, guess, username);
    }
    public boolean verifying(String username, String password){
        return u.verifying(username,password);
    }
}

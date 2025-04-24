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
 
    public void setUser(String password, String username, int Amount){
        u.esstablish(username,password,Amount);
    }
    public ArrayList<String> getLeaderBoard(){
        return sb.getPlayers();
    }

    public String getResults(){
        if(g.Flip()){
            return "HEADS";
        }
        else{
            return "TALLS";
        }
    }

}

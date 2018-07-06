
import java.util.ArrayList;


public class Giocatore {
    private String nickname;
    private ArrayList RobotList;

    public Giocatore(String nickname, ArrayList RobotList) {
        this.nickname = nickname;
        this.RobotList = RobotList;
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList getRobotList() {
        return RobotList;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRobotList(ArrayList RobotList) {
        this.RobotList = RobotList;
    }
    
    
}

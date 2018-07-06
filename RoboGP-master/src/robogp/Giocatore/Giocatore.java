package robogp.Giocatore;

import java.util.ArrayList;


public class Giocatore {
    private String nickname;
    private ArrayList RobotList;
    private Upgrade upgrade;

    public Giocatore(String nickname, ArrayList RobotList) {
        this.nickname = nickname;
        this.RobotList = RobotList;
        upgrade = null;
    }

    public String getNickname() {
        return nickname;
    }
    
    public Upgrade getUpgrade() {
        return upgrade;
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

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }
    
}

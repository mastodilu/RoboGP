package robogp.Giocatore;

import java.util.ArrayList;
import robogp.Giocatore.Robot.RobotMarkerPlaying;


public class Giocatore {
    private String nickname;
    private ArrayList<RobotMarkerPlaying> robotList;
    private Upgrade upgrade;
    
    public Giocatore(String nickname){
        this.nickname = nickname;
        this.upgrade = null;
        this.robotList = new ArrayList<RobotMarkerPlaying>();
    }
    
    public Giocatore(String nickname, RobotMarkerPlaying robot){
        this.nickname = nickname;
        this.robotList = new ArrayList<RobotMarkerPlaying>();
        this.robotList.add(robot);
        this.upgrade = null;
    }

    public Giocatore(String nickname, ArrayList<RobotMarkerPlaying> RobotList) {
        this.nickname = nickname;
        this.robotList = RobotList;
        this.upgrade = null;
    }
    
    
    public void assegnaRobot(RobotMarkerPlaying robot){
        this.robotList.add(robot);
    }
    
    public void rimuoviRobot(RobotMarkerPlaying robot){
        this.robotList.remove(robot);
    }
    
    public String getNickname(){
        return this.nickname;
    }
    
}

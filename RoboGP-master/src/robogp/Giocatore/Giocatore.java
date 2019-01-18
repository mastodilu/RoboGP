package robogp.Giocatore;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;


public class Giocatore {
    private String nickname;
    private ArrayList<RobotMarker> robotList;
    private Upgrade upgrade;
    
    public Giocatore(String nickname){
        this.nickname = nickname;
        this.upgrade = null;
        this.robotList = new ArrayList<RobotMarker>();
    }
    
    public Giocatore(String nickname, RobotMarker robot){
        this.nickname = nickname;
        this.robotList = new ArrayList<RobotMarker>();
        this.robotList.add(robot);
        this.upgrade = null;
    }

    public Giocatore(String nickname, ArrayList<RobotMarker> RobotList) {
        this.nickname = nickname;
        this.robotList = RobotList;
        this.upgrade = null;
    }
    
    
    public void assegnaRobot(RobotMarker robot){
        this.robotList.add(robot);
    }
    
    public void rimuoviRobot(RobotMarker robot){
        this.robotList.remove(robot);
    }
    
    public String getNickname(){
        return this.nickname;
    }
    
    @Override
    public String toString(){
        String result = nickname+" upgrade: ";
        if(upgrade != null)
            result += upgrade.toString();
        else result += "N/A";
        result += " robots: ";
        for(RobotMarker robot : this.robotList)
            result += robot.toString();
        return result;
    }
}

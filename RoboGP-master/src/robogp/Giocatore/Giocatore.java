package robogp.Giocatore;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;


public class Giocatore {
    private String nickname;
    private ArrayList<RobotMarker> robotList;
    
    public Giocatore(String nickname){
        this.nickname = nickname;
        this.robotList = new ArrayList<RobotMarker>();
    }
    
    public Giocatore(String nickname, RobotMarker robot){
        this.nickname = nickname;
        this.robotList = new ArrayList<RobotMarker>();
        this.robotList.add(robot);
    }

    public Giocatore(String nickname, ArrayList<RobotMarker> RobotList) {
        this.nickname = nickname;
        this.robotList = RobotList;
    }
    
    
    public void assegnaRobot(RobotMarker robot){
        this.robotList.add(robot);
    }

    public ArrayList<RobotMarker> getRobotList() {
        return robotList;
    }
    
    public void rimuoviRobot(RobotMarker robot){
        this.robotList.remove(robot);
    }
    
    public String getNickname(){
        return this.nickname;
    }
    
    
    @Override
    public String toString(){
        String result = nickname;
        result += " robots: ";
        for(RobotMarker robot : this.robotList)
            result += robot.toString();
        return result;
    }
}

package robogp.Giocatore.Robot;

import robogp.matchmanager.RobotMarker;

import java.util.ArrayList;


public class RobotMarkerTraining extends RobotMarker {
 private ArrayList storicoDirections;
 private ArrayList storicoDocks;

    public RobotMarkerTraining(
                    char startDirection,
                    String name,
                    String color,
                    String owner,
                    int startDock) {
        super(name, color); // usiamo il costruttore che ci hanno dato
        this.assign(owner, startDock);
        storicoDirections = new ArrayList();
        storicoDocks = new ArrayList();
        storicoDirections.add(startDirection);
        storicoDocks.add(startDock);
    }

    public ArrayList getStoricoDirections() {
        return storicoDirections;
    }

    public ArrayList getStoricoDocks() {
        return storicoDocks;
    }

    public void setStoricoDirections(ArrayList storicoDirections) {
        this.storicoDirections = storicoDirections;
    }

    public void setStoricoDocks(ArrayList storicoDocks) {
        this.storicoDocks = storicoDocks;
    }
 
 
}

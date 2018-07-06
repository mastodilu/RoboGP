
import java.util.ArrayList;


public class RobotMarkerTraining extends RobotMarker {
 private ArrayList storicoDirections;
 private ArrayList storicoPosx;
 private ArrayList storicoPosy;

    public RobotMarkerTraining(String name, String color, int posx, int posy, String owner, char direction, int salute, int vite) {
        super(name, color, posx, posy, owner, direction, salute, vite);
        storicoDirections = new ArrayList();
        storicoPosx = new ArrayList();
        storicoPosy = new ArrayList();
    }

    public ArrayList getStoricoDirections() {
        return storicoDirections;
    }

    public ArrayList getStoricoPosx() {
        return storicoPosx;
    }

    public ArrayList getStoricoPosy() {
        return storicoPosy;
    }

    public void setStoricoDirections(ArrayList storicoDirections) {
        this.storicoDirections = storicoDirections;
    }

    public void setStoricoPosx(ArrayList storicoPosx) {
        this.storicoPosx = storicoPosx;
    }

    public void setStoricoPosy(ArrayList storicoPosy) {
        this.storicoPosy = storicoPosy;
    }
 
 
}

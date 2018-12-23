package robogp.Giocatore.Robot;

import robogp.matchmanager.RobotMarker;

import java.util.ArrayList;
import robogp.robodrome.Direction;


public class RobotMarkerTraining extends RobotMarker {
    private ArrayList<Direction> storicoDirezioni;
    private ArrayList<Posizione> storicoPosizioni;

    /**
     * costruttore di RobotMarkerTraining
     * @param name nome del robot
     * @param color colore del robot
     */
    public RobotMarkerTraining( String name, String color) {
        super(name, color);
        storicoDirezioni = new ArrayList();
        storicoPosizioni = new ArrayList();
    }

    public ArrayList getStoricoDirections() {
        return storicoDirezioni;
    }

    public ArrayList getStoricoPosizioni() {
        return storicoPosizioni;
    }

    public void setStoricoDirezioni(ArrayList storicoDirezioni) {
        this.storicoDirezioni = storicoDirezioni;
    }

    public void setStoricoPosizioni(ArrayList storicoPosizioni) {
        this.storicoPosizioni = storicoPosizioni;
    }
    
    /**
     * salva la posizione corrente del robot nell'array
     * @param r indice della riga da salvare
     * @param c indice della colonna da salvare
     */
    public void updatePosizione(int r, int c){
        this.storicoPosizioni.add(new Posizione(r, c));
    }
    
    
    public Posizione getCurrentPos(){
        return this.storicoPosizioni.get(this.storicoPosizioni.size() - 1);
    }
    
    public Direction getCurrentDirection(){
        return this.storicoDirezioni.get(this.storicoDirezioni.size() - 1);
    }
    
    
    
 

 
}

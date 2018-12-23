package robogp.Giocatore.Robot;

import robogp.matchmanager.RobotMarker;
import robogp.robodrome.Direction;

public class RobotMarkerPlaying extends RobotMarker{
//per sapere la posizione del robot (e anche quella iniziale) guardiamo 'dockNumber' della super classe invece di startX-Y
private final Direction startDirection; //direzione iniziale
private final int saluteMax;
private boolean spento;
private int contatoreDanni;

    public RobotMarkerPlaying(
                    int startDock, Direction startDirection,
                    int saluteMax, int vite,
                    String name, String color, String owner ){
        super(name, color); // usiamo il costruttore che ci hanno dato
        this.assign(owner, startDock); //assegna owner e posizione iniziale
        this.startDirection = startDirection;   //teniamo in memoria la direzione iniziale e
        this.setDirection(startDirection);      // aggiorniamo la posizione corrente
        this.saluteMax = saluteMax;
        this.spento = false; //cominciano con un robot accesso, eventualmente si spegne durante il turno
        this.contatoreDanni = 0;
        this.setSalute(saluteMax);
        this.setVite(vite);
    }

    public Direction getStartDirection() {
        return startDirection;
    }

    public int getSaluteMax() {
        return saluteMax;
    }

    public boolean isSpento() {
        return spento;
    }

    public int getContatoreDanni() {
        return contatoreDanni;
    }

    public void setSpento(boolean spento) {
        this.spento = spento;
    }

    public void setContatoreDanni(int contatoreDanni) {
        this.contatoreDanni = contatoreDanni;
    }
    
}

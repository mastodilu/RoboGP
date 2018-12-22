package robogp.matchmanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import robogp.robodrome.Direction;
import robogp.robodrome.image.ImageUtil;

/**
 *
 * @author claudia
 */
public class RobotMarker implements Serializable {

    private transient BufferedImage robotImage; // transient dice al compilatore che la variabile non viene serializzata
    private final String name;
    private final String color;
    private String owner;
    /**
     * dock di partenza assegnato al robot
     */
    private int dockNumber;
    private int riga; //posizione corrente del robot
    private int colonna; //posizione corrente del robot
    private int salute, vite; //salute e vita correnti del robot
    /**
     * Direzione del robot.
     */
    Direction direction = Direction.E;

    public RobotMarker(String name, String color) {
        this.name = name;
        this.color = color;
        this.dockNumber = 0;
    }

    public BufferedImage getImage(int size) {
        if (this.robotImage == null) {
            String imgFile = "robots/" + name + ".png";
            try {
                robotImage = ImageIO.read(new File(imgFile));
            } catch (IOException ex) {
                Logger.getLogger(RobotMarker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ImageUtil.scale(ImageUtil.superImpose(null, this.robotImage),size, size);
    }
    
    /**
     * Gira il robot a sinistra.
     */
    public void sinistra(){
        if(direction == Direction.E)
            direction = Direction.N;
        else if(direction == Direction.W)
            direction = Direction.S;
        else if(direction == Direction.N)
            direction = Direction.W;
        else if(direction == Direction.S)
            direction = Direction.E;
    }
    
    /**
     * Gira il robot a destra.
     */
    public void destra(){
        if(direction == Direction.E)
            direction = Direction.S;
        else if(direction == Direction.W)
            direction = Direction.N;
        else if(direction == Direction.N)
            direction = Direction.E;
        else if(direction == Direction.S)
            direction = Direction.W;}

//    assegna il robot al giocatore
    public void assign(String nickname, int dock) {
        this.owner = nickname;
        this.dockNumber = dock;
    }

    public void free() {
        this.owner = null;
        this.dockNumber = -1;
    }

    public boolean isAssigned() {
        return (this.owner != null);
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return name;
    }
    
    public int getSalute() {
        return salute;
    }

    public int getVite() {
        return vite;
    }

    public int getDock() {
        return dockNumber;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setSalute(int salute) {
        this.salute = salute;
    }

    public void setVite(int vite) {
        this.vite = vite;
    }
    
    public void setDirection(Direction d){
        this.direction = d;
    }
    
    public Direction getDirection(){
        return this.direction;
    }
    
    public void setPosizione(int r, int c){
        this.riga = r;
        this.colonna = c;
    }
    
}

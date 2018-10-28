package robogp.matchmanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private int dockNumber; //posizione corrente del robot
    private int salute, vite; //salute e vita correnti del robot
    private char direction;

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

//    assegna il robot al giocatore
    public void assign(String nickname, int dock) {
        this.owner = nickname;
        this.dockNumber = dock;
    }

    public void free() {
        this.owner = null;
        this.dockNumber = 0;
    }

    public boolean isAssigned() {
        return (this.dockNumber > 0);
    }

    public String getOwner() {
        return this.owner;
    }

    public int getDock() {
        return this.dockNumber;
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

    public void setSalute(int salute) {
        this.salute = salute;
    }

    public void setVite(int vite) {
        this.vite = vite;
    }
    
    public void setDirection(char c){
        this.direction = c;
    }
    
    public char getDirection(){
        return this.direction;
    }
    
}

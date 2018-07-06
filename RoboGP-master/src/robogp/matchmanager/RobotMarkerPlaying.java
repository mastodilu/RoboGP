
public class RobotMarkerPlaying extends RobotMarker{
    
private final int startX;
private final int startY;
private final char startDirection;
private final int saluteMax;
private boolean spento;
private int contatoreDanni;

    public RobotMarkerPlaying(int startX, int startY, char startDirection, int saluteMax, boolean spento, String name, String color, int posx, int posy, String owner, char direction, int salute, int vite) {
        super(name, color, posx, posy, owner, direction, salute, vite);
        this.startX = startX;
        this.startY = startY;
        this.startDirection = startDirection;
        this.saluteMax = saluteMax;
        this.spento = spento;
        this.contatoreDanni = 0;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public char getStartDirection() {
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

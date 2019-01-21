package robogp.matchmanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import robogp.deck.InstructionCard;
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
    private final Direction startDirection; //direzione iniziale
private final int saluteMax;
private boolean spento;
private int contatoreDanni;
private final InstructionCard[] registri = new InstructionCard[5];

    public RobotMarker(int startDock, Direction startDirection,int saluteMax, int vite,String name, String color, String owner ){
        this.assign(owner, startDock); //assegna owner e posizione iniziale
        this.startDirection = startDirection;   //teniamo in memoria la direzione iniziale e
        this.saluteMax = saluteMax;
        this.spento = false; //cominciano con un robot accesso, eventualmente si spegne durante il turno
        this.contatoreDanni = 0;
        this.setSalute(saluteMax);
        this.setVite(vite);
        this.name = name;
        this.color=color;
        this.dockNumber = 0;
        storicoDirezioni = new ArrayList();
        storicoPosizioni = new ArrayList();
    }
    /**
     * Nome del giocatore al quale e' stato assegnato il robot.
     */
    private String owner;
    
    /**
     * dock di partenza assegnato al robot
     */
    private int dockNumber;
    private int salute, vite; //salute e vita correnti del robot

    /**
     * Le direzioni assunte dal robot in ordine cronologico.
     */
    private ArrayList<Direction> storicoDirezioni;
    
    /**
     * Le posizioni assunte dal robot in ordine cronologico.
     */
    private ArrayList<Posizione> storicoPosizioni;

    public RobotMarker(String name, String color) {
        this.startDirection = Direction.E;   //teniamo in memoria la direzione iniziale e
        this.saluteMax = 10;
        this.spento = false; //cominciano con un robot accesso, eventualmente si spegne durante il turno
        this.contatoreDanni = 0;
        this.setSalute(saluteMax);
        this.setVite(vite);
        this.name = name;
        this.color=color;
        this.dockNumber = 0;
        storicoDirezioni = new ArrayList();
        storicoPosizioni = new ArrayList();
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
     * Assegna il robot corrente al giocatore specificato e gli assegna un dock.
     * @param nickname giocatore
     * @param dock numero di dock
     */
    public void assign(String nickname, int dock) {
        this.owner = nickname;
        this.dockNumber = dock;
    }

    /**
     * Rimuove l'assegnatario dal robot.
     */
    public void free() {
        this.owner = null;
    }

    public boolean isAssigned() {
        return (this.owner != null);
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
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

    public void setSalute(int salute) {
        this.salute = salute;
    }

    public void setVite(int vite) {
        this.vite = vite;
    }
    
    
//    public void setPosizione(int r, int c){
//        this.riga = r;
//        this.colonna = c;
//    }
    
    public ArrayList getStoricoDirections() {
        return storicoDirezioni;
    }

    public ArrayList getStoricoPosizioni() {
        return storicoPosizioni;
    }

//    public void setStoricoDirezioni(ArrayList storicoDirezioni) {
//        this.storicoDirezioni = storicoDirezioni;
//    }
//
//    public void setStoricoPosizioni(ArrayList storicoPosizioni) {
//        this.storicoPosizioni = storicoPosizioni;
//    }
    
//    /**
//     * salva la posizione corrente del robot nell'array
//     * @param r indice della riga da salvare
//     * @param c indice della colonna da salvare
//     */
//    public void updateStoricoPosizioni(int r, int c){
//        this.storicoPosizioni.add(new Posizione(r, c));
//    }
//    
//    
//    public void updateStoricoDirezioni(Direction dir){
//        this.storicoDirezioni.add(dir);
//    }
//    
    
    /**
     * @return la posizione corrente occupata dal robot
     */
    public Posizione getLastPosition(){
        if(this.storicoPosizioni.size() == 0)
            return null;
        return this.storicoPosizioni.get(this.storicoPosizioni.size() - 1);
    }
    
    public Direction getLastDirection(){
        if(this.storicoDirezioni.size() == 0)
            return null;
        return this.storicoDirezioni.get(this.storicoDirezioni.size() - 1);
    }
    
    
    /**
     * Aggiorna posizione e direzione del robot e li aggiunge allo storico.
     * @param riga riga corrente
     * @param colonna colonna corrente
     * @param dir direzione corrente
     */
    public void updatePosizione(int riga, int colonna, Direction dir){
        storicoPosizioni.add(new Posizione(riga, colonna));
        storicoDirezioni.add(dir);
    }
    
    
    /**
     * Cancella l'ultima posizione raggiunta dal robot.
     */
    public void cancellaUltimaPosizione(){
        if(this.storicoPosizioni.size() > 0){
            this.storicoPosizioni.remove(this.storicoPosizioni.size()-1);
        }
    }
    
    
    /**
     * Cancella l'ultima direzione assunta dal robot.
     */
    public void cancellaUltimaDirezione(){
        if(this.storicoDirezioni.size() > 0)
            this.storicoDirezioni.remove(this.storicoDirezioni.size()-1);
    }
    
    
    /**
     * Resetta alcune variabili del robot.
     */
    public void reset(){
        this.storicoDirezioni = new ArrayList<Direction>();
        this.storicoPosizioni = new ArrayList<Posizione>();
    }
    
    @Override
    public String toString(){
        String s = "";
        s += this.color + ""
                + this.getLastPosition().getRiga() + "x" + this.getLastPosition().getColonna()
                + this.getLastDirection();
        return s;
    }
    
}

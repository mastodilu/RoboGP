package robogp.Giocatore.Robot;

import connection.Message;
import java.util.ArrayList;
import robogp.deck.InstructionCard;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.Direction;

public class RobotMarkerPlaying extends RobotMarker{
//per sapere la posizione del robot (e anche quella iniziale) guardiamo 'dockNumber' della super classe invece di startX-Y
private final Direction startDirection; //direzione iniziale
private final int saluteMax;
private boolean spento;
private int contatoreDanni;
private final InstructionCard[] registri = new InstructionCard[5];

    public RobotMarkerPlaying(
                    int startDock, Direction startDirection,
                    int saluteMax, int vite,
                    String name, String color, String owner ){
        super(name, color); // usiamo il costruttore che ci hanno dato
        this.assign(owner, startDock); //assegna owner e posizione iniziale
        this.startDirection = startDirection;   //teniamo in memoria la direzione iniziale e
        this.saluteMax = saluteMax;
        this.spento = false; //cominciano con un robot accesso, eventualmente si spegne durante il turno
        this.contatoreDanni = 0;
        this.setSalute(saluteMax);
        this.setVite(vite);
    }
    
    public void stampaRegistri(){
        System.out.println("[ROBOT" + this.getName() + ":");
        int i = 0;
        while (i<5){
            System.out.println("    - " + i + ": " + this.registri[i].getTipo() + ";" );
        }
        System.out.println("]");
    }
    
    public void aggiungiScheda(InstructionCard scheda, int num){
        if (num < 5){
            this.registri[num] = scheda;
        }
    }
    
    //numero è il numero del registro da svuotare (da 0 a 4)
    public void rimuoviScheda(int numero){
        this.registri[numero] = null;
        int i = numero;
        while (i < this.registri.length -1){
            this.registri[i] = this.registri[i+1];
            i++;
        }
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

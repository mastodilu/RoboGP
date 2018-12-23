package robogp.deck;
import robogp.robodrome.Direction;
import robogp.robodrome.Rotation;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class InstructionCard{

    private String tipo;//uturn backup move3 move2 move1 turnleft turnright

    /**
     * priorita' della carta
     */
    private int priorita;
    private int priorita_min; //usato per calcolare 'priorita'
    private int priorita_max; //usato per calcolare 'priorita'
    
    private String path_icona;
    
    /**
     * il numero di caselle di cui si muove
     */
    private int movimento;
    
    /**
     * la direzione del movimento
     */
    private Rotation rotazione;
    

    public InstructionCard(String _tipo){
        switch (_tipo){
            case "uturn":{
                createUTurn();
                break;
            }
            case "turnleft":{
                createTurnLeft();
                break;
            }
            case "turnright":{
                createTurnRight();
                break;
            }
            case "backup":{
                createBackUp();
                break;
            }
            case "move1":{
                createMove1();
                break;
            }
            case "move2":{
                createMove2();
                break;
            }
            case "move3":{
                createMove3();
                break;
            }
        }
    }
    // -costruttore

        private void createUTurn(){
        this.tipo = "uturn";
        this.priorita_min = 10;
        this.priorita_max = 60;
        this.path_icona = "./icons/card-uturn.png";
        this.movimento = 0; // si muove di 0 caselle
        this.rotazione = Rotation.CW180;// ruota a sinistra di 90 gradi
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createTurnLeft(){
        this.tipo = "turnleft";
        this.priorita_min = 70;
        this.priorita_max = 410;
        this.path_icona = "./icons/card-turnL.png";
        this.movimento = 0; // si muove di 0 caselle
        this.rotazione = Rotation.CCW90;// ruota a sinistra di 90 gradi
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createTurnRight(){
        this.tipo = "turnright";
        this.priorita_min = 80;
        this.priorita_max = 420;
        this.path_icona = "./icons/card-turnR.png";
        this.movimento = 0; // si muove di 0 caselle
        this.rotazione = Rotation.CW90;// ruota a destra di 90 gradi
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createBackUp(){
        this.tipo = "backup";
        this.priorita_min = 430;
        this.priorita_max = 480;
        this.path_icona = "./icons/card-backup.png";
        this.movimento = 1; // si muove indietro di 1 casella
        this.rotazione = Rotation.NO;
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove1(){
        this.tipo = "move1";
        this.priorita_min = 490;
        this.priorita_max = 660;
        this.path_icona = "./icons/card-move1.png";
        this.movimento = 1; // si muove di 1 casella
        this.rotazione = Rotation.NO;// non c'e' rotazione
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove2(){
        this.tipo = "move2";
        this.priorita_min = 670;
        this.priorita_max = 780;
        this.path_icona = "./icons/card-move2.png";
        this.movimento = 2; // si muove di 2 caselle
        this.rotazione = Rotation.NO;// non c'e' rotazione
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove3(){
        this.tipo = "move3";
        this.priorita_min = 790;
        this.priorita_max = 840;
        this.path_icona = "./icons/card-move3.png";
        this.movimento = 3; // si muove di 3 caselle
        this.rotazione = Rotation.NO;// non c'e' rotazione
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    @Override
    public String toString(){
        String s = "[";
        s += tipo + ":" + getPriorita() + "]";
        return s;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPath_icona() {
        return path_icona;
    }

    public int getPriorita() {
        return priorita;
    }

    public int getMovimento() {
        return movimento;
    }

    public Rotation getRotazione() {
        return rotazione;
    }
    
    private int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
    

}

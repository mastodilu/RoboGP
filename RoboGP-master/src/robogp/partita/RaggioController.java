package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BoardCell;
import robogp.robodrome.Direction;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class RaggioController {
    
    /**
     * Array di robot in partita.
     */
    private ArrayList<RobotMarker> robots;
    
    /**
     * Robodromo.
     */
    private Robodrome robodromo;
    
    /**
     * View del robodromo.
     */
    private RobodromeView rv;
    
    /**
     * Gestione pattern singleton.
     */
    private static RaggioController singleInstance;
    
    /**
     * Costruttore.
     */
    private RaggioController(){}
    
    public static RaggioController getInstance(){
        if(singleInstance == null)
            singleInstance = new RaggioController();
        return singleInstance;
    }
    
    /**
     * Inizializza la classe se qualche variabile non e' stata inizializzata.
     * @param view
     * @param rd
     * @param rs 
     */
    public void init(RobodromeView view, Robodrome rd, ArrayList<RobotMarker> rs){
        if(!checkInit()){
            this.rv = view;
            this.robodromo = rd;
            this.robots = rs;
        }
    }
    
    
    /**
     * True se tutte le variabili di istanza sono diverse da null
     * e c'e' almeno un robot in partita.
     * @return true se l'istanza e' inizializzata correttamente,
     * false altrimenti.
     */
    public boolean checkInit(){
        return robots != null
                && robodromo != null
                && rv != null
                && robots.size() > 0;
    }
    
    
    
    /**
     * Restituisce la direzione opposta a quella passata.
     * @param d direzione
     * @return direzione opposta
     */
    private Direction direzioneOpposta(Direction d) {
        if(d == Direction.W)        return Direction.E;
        else if(d == Direction.N)   return Direction.S;
        else if(d == Direction.E)   return Direction.W;
        else                        return Direction.N;
    }
    
    
    
    /**
     * Controlla tutti i robot e si blocca quando
     * trova quello nella cella passata come parametro.
     * @param cella che potrebbe contenere un robot
     * @return il robot nella cella specificata, null altrimenti
     */
    private RobotMarker getRobotInCella(BoardCell cella){
        for(RobotMarker robot : robots){
            int riga, colonna;
                riga = robot.getLastPosition().getRiga();
                colonna = robot.getLastPosition().getColonna();
            if(riga == cella.getRiga()
                    && colonna == cella.getColonna())
                return robot;
        }
        return null;
    }
    
    
    //TODO spara
    //  TODO cellaFinale
    //      TODO hasWall(), hasRobot
    
    
    /**
     * Restituisce la cella successiva del robodromo rispetto alla posizione
     * e alla direzione corrente del robot.
     * @param cella la cella di riferimento
     * @param direzione del movimento rispetto alla cella specificata
     * @return la cella successiva se esiste, altrimenti null.
     */
    private BoardCell cellaSuccessiva(BoardCell cella, Direction direzione) {
        int riga = cella.getRiga();
        int colonna = cella.getColonna();
        if(direzione == Direction.W)        return this.robodromo.getCell(riga, colonna-1);
        else if(direzione == Direction.N)   return this.robodromo.getCell(riga-1, colonna);
        else if(direzione == Direction.E)   return this.robodromo.getCell(riga, colonna+1);
        else                                return this.robodromo.getCell(riga+1, colonna);
    }
    
    
    
    /**
     * Restituisce true se muovendocisi nella direzione passata
     * si incontrano delle mura entrando nella cella.
     * @param cella considerata
     * @param direzione del movimento
     * @return true se si incontrano delle mura entrando nella cella,
     * false altrimenti
     */
    private boolean muraIniziali(BoardCell cella, Direction direzione){
        direzione = direzioneOpposta(direzione);
        return cella.hasWall(direzione);
    }
    
    
    
    /**
     * Restituisce true se si incontrano delle mura uscendo dalla cella
     * rispetto alla direzione del movimento.
     * @param cella considerata
     * @param direzione del movimento
     * @return true se si incontrano delle mura uscendo dalla cella,
     * false altrimenti.
     */
    private boolean muraFinali(BoardCell cella, Direction direzione){
        return cella.hasWall(direzione);
    }
    
    
    /**
     * Restituisce true se la cella successiva rispetto alla direzione passata
     * e alla cella da cui ci si muove e' fuori dalla mappa.
     * @param cella considerata
     * @param direzione del movimento
     * @return true se la cella successiva e' fuori dalla mappa,
     * false altrimenti.
     */
    private boolean isBordo(BoardCell cella, Direction direzione){
        return cellaSuccessiva(cella, direzione) == null;
    }
    
}

package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
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
    
    
    //TODO spara
    //  TODO cellaFinale
    //      TODO hasWall(), hasRobot
    
    
}
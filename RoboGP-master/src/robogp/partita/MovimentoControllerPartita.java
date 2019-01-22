package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 * Classe che controlla la logica del movimento prima di applicarne l'animazione.
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class MovimentoControllerPartita {
    
    /**
     * View del robodromo della partita.
     */
    private RobodromeView rv;
    
    /**
     * Robodromo della partita.
     */
    private Robodrome robodrome;
    
    /**
     * Lista di robot in partita.
     */
    private ArrayList<RobotMarker> robot;
    
    /**
     * Pattern singleton.
     */
    private static MovimentoControllerPartita singleInstance;
    
    
    /**
     * Costruttore.
     * @param rv View del robodromo
     * @param rd robodromo
     * @param rb arraylist di RobotMarker, i robot nella partita
     */
    private MovimentoControllerPartita(RobodromeView rv, Robodrome rd, ArrayList<RobotMarker> rb){
        this.robodrome = rd;
        this.rv = rv;
        this.robot = rb;
    }
}

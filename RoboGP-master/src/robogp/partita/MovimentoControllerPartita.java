package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BoardCell;
import robogp.robodrome.Direction;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 * Classe che controlla la logica del movimento per la partita
 * prima di applicarne l'animazione.
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
     */
    private MovimentoControllerPartita(){
    }
    
    /**
     * Inizializza la classe.
     * @param rv View del robodromo
     * @param rd robodromo
     * @param rb arraylist di RobotMarker, i robot nella partita
     */
    public void init(RobodromeView rv, Robodrome rd, ArrayList<RobotMarker> rb){
        this.robodrome = rd;
        this.rv = rv;
        this.robot = rb;
    }
    
    
    /**
     * @return l'unica istanza della classe.
     */
    public MovimentoControllerPartita getInstance(){
        if(singleInstance == null)
            singleInstance = new MovimentoControllerPartita();
        return singleInstance;
    }
    
    
    /**
     * Cancella l'unica istanza della classe.
     */
    public void deleteInstance(){
        singleInstance = null;
    }
    
    
    
    /**
     * @return true se la classe e' stata correttamente istanziata.
     */
    public boolean checkInit(){
        return this.robodrome != null
                && this.robot != null
                && this.rv != null;
    }
    
    
    
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
        if(direzione == Direction.W)        return this.robodrome.getCell(riga, colonna-1);
        else if(direzione == Direction.N)   return this.robodrome.getCell(riga-1, colonna);
        else if(direzione == Direction.E)   return this.robodrome.getCell(riga, colonna+1);
        else                                return this.robodrome.getCell(riga+1, colonna);
    }


    // TODO: controlla muri
    // TODO: 
    
}

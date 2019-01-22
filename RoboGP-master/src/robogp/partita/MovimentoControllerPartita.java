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
    private ArrayList<RobotMarker> robots;
    
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
        this.robots = rb;
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
                && this.robots != null
                && this.rv != null;
    }
    
    
    
    /**
     * Controlla che il robot possa avanzare di una casella.
     * @param cella dal quale ci si muove
     * @param direzione del movimento
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(BoardCell cella, Direction direzione){
        if(bloccatoDaBordi(cella, direzione))   return false;
        if(bloccatoDaMuri(cella, direzione))    return false;
        return true;
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
    
    
    
    
    /**
     * Controlla che il movimento rispetto nella direzione specificata
     * rispetto alla cella specificata non sia bloccato da bordi.
     * @param cella la cella dal quale ci si sposta
     * @param direzione la direzione del movimento
     * @return true se gli indici escono dalla mappa, false altrimenti.
     */
    private boolean bloccatoDaBordi(BoardCell cella, Direction direzione){
        int riga = cella.getRiga();
        int colonna = cella.getColonna();
        if(direzione == Direction.W && colonna == 0)
            return true;
        else if(direzione == Direction.N && riga == 0)
            return true;
        else if(direzione == Direction.E && colonna == this.robodrome.getColumnCount()-1)
            return true;
        else if(direzione == Direction.S && riga == this.robodrome.getRowCount()-1)
            return true;
        return false;
    }
    
    
    
    
    /**
     * Restituisce il robot nella cella successiva.
     * @param cella cella di partenza
     * @param direzione direzione del movimento
     * @return il robot nella cella successiva a quello corrente, altrimenti null
     */
    public RobotMarker robotInCellaSuccessiva(BoardCell cella, Direction direzione) {
        BoardCell next = cellaSuccessiva(cella, direzione);
        boolean flag = next.hasRobot();
        
        System.out.printf("Cella successiva has robot: %b\n", flag);
        
        if(flag){
            int nextR, nextC, r, c;
            nextR = next.getRiga();
            nextC = next.getColonna();
            for(RobotMarker robotmarker : this.robots){
                    r = robotmarker.getLastPosition().getRiga();
                    c = robotmarker.getLastPosition().getColonna();
                if(nextR == r && nextC == c)
                    return robotmarker;
            }
        }
        return null;
    }
    
    
    
    
    /**
     * Controlla la presenza di muri uscendo dalla cella specificata
     * e entrando nella cella successiva rispetto alla direzione del movimento.
     * @param cella dalla quale ci si sposta
     * @param direzione del movimento
     * @return true se ci sono muri che bloccano, false altrimenti.
     */
    private boolean bloccatoDaMuri(BoardCell cella, Direction direzione) {
        Direction opposta = direzioneOpposta(direzione);
        BoardCell successiva = cellaSuccessiva(cella, direzione);
        
        //controlla muro in questa cella
        if(cella.hasWall(direzione))     return true;
        //controlla muri nella cella successiva
        if(successiva == null || successiva.hasWall(opposta))
            return true;

        return false;
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
     * Piazza il robot in una posizione iniziale specificata a mano.
     * @param rm robot
     * @param d direzione
     * @param r riga
     * @param c colonna
     */
    public void placeRobot(RobotMarker rm, Direction d, int r, int c){
        BoardCell cella = this.robodrome.getCell(r, c);
        if(cella != null){
            rv.placeRobot(rm, d, r, c, true); // aggiunge il robot al tabellone
            cella.robotInside();
            rm.updatePosizione(r, c, d);
        }
    }
    
    
    

    /**
     * Avvia l'esecuzione delle animazioni
     */
    public void playAnimations(){
        rv.play();
    }


}

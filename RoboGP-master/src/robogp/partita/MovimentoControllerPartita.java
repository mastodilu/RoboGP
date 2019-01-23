package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BeltCell;
import robogp.robodrome.BoardCell;
import robogp.robodrome.Direction;
import robogp.robodrome.Robodrome;
import robogp.robodrome.Rotation;
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
     * @param cella dalla quale ci si muove
     * @param direzione del movimento
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(BoardCell cella, Direction direzione){
        if(bucoNero(cella))                     return false;
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
     * @return true se la cella corrente è un buco nero,
     *  false altrimenti
     */
    private boolean bucoNero(BoardCell cella){
        return cella.getType() == 'P';
    }
    
    
    /**
     * Simula il movimento del robot di tot passi nella direzione
     * specificata contando i passi compiuti.
     * NB: l'animazione va gestita separatamente.
     * @param robot da muovere
     * @param passiDaFare da compiere nella direzione indicata
     * @param direzione del movimento
     * @return il passi compiuti.
     */
    private int simulaMovimento(RobotMarker robot, int passiDaFare, Direction direzione){
        int contaPassi = 0;
        BoardCell cella = this.robodrome.getCell(
                    robot.getLastPosition().getRiga(),
                    robot.getLastPosition().getColonna()
                );
        for(int i = 0; i < passiDaFare; i++){
            if(movimentoAmmissibile(cella, direzione)){
                contaPassi++;
                cella = this.cellaSuccessiva(cella, direzione);
            }
        }
        return contaPassi;
    }
    
    
    
    /**
     * Restituisce la cella raggiunta sapendo
     * la cella di partenza, la direzione e i passi compiuti.
     * @param cella di partenza
     * @param direzione del movimento
     * @param passi compiuti
     * @return la cella raggiunta
     * @throws Exception se la cella calcolata esce dal robodromo
     */
    private BoardCell cellaRaggiunta(BoardCell cella, Direction direzione, int passi){
        int riga = cella.getRiga();
        int colonna = cella.getColonna();
        BoardCell cellaRaggiunta;
        if(direzione == Direction.W)        cellaRaggiunta = this.robodrome.getCell(riga, colonna-passi);
        else if(direzione == Direction.N)   cellaRaggiunta = this.robodrome.getCell(riga-passi, colonna);
        else if(direzione == Direction.E)   cellaRaggiunta = this.robodrome.getCell(riga, colonna+passi);
        else                                cellaRaggiunta = this.robodrome.getCell(riga+passi, colonna);
        return cellaRaggiunta;
    }
    
    
    /**
     * Se il robot si trova su un nastro trasportatore semplice
     * lo fa slittare di una posizione nella direzione del nastro se
     * il movimento non e' ostacolato.
     * @param robot da spostare
     */
    private void nastriSemplici(RobotMarker robot){
        int riga, colonna;
            riga = robot.getLastPosition().getRiga();
            colonna = robot.getLastPosition().getColonna();
        BoardCell cella = this.robodrome.getCell(riga, colonna);
        if(cella.getType() == 'B'){ // nastro trasportatore semplice
            BeltCell belt = (BeltCell)cella;
            Direction direzione = belt.getOutputDirection();
            if(this.movimentoAmmissibile(belt, direzione)){
                // muove di 1 nella direzione specificata senza compiere rotazione
                muovi(robot, 1, direzione, Rotation.NO);
            }
        }
    }
    
    
    //TODO nastriTrasportatoriSemplici
    //TODO nastriTrasportatoriExpress
    
    //TODO metodo per spingere i robot durante il movimento
    
    //TODO metodo per aggiornare la posizione del robot
    //TODO metodo per aggiornare la variabile BoardCell.hasRobot
    
    //TODO metodo che aggiunge l'animazione del movimento compiuto
        //muovi(robot, nPassi, direzione, rotazione)
    
    
    /**
     * Aggiunge l'animazione di movimento del robot e aggiorna le variabili
     * di robot e cella.
     * @param robot da muovere
     * @param passi compiuti
     * @param direzione direzione del movimento
     * @param rotazione rotazione compiuta dal robot
     */
    private void muovi(RobotMarker robot, int passi, Direction direzione, Rotation rotazione) {
        BoardCell cella, raggiunta;
        
        // cella corrente del robot
        cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(), 
                robot.getLastPosition().getColonna()
        );
        raggiunta = cellaRaggiunta(cella, direzione, passi);

        //aggiorna variabili celle
        cella.robotOutside();
        raggiunta.robotInside();
        robot.updatePosizione(raggiunta.getRiga(), raggiunta.getColonna(), robot.getLastDirection());
        
        //animazione movimento
        this.rv.addRobotMove(robot, passi, direzione, rotazione);
    }
    

    /**
     * Avvia l'esecuzione delle animazioni
     */
    public void playAnimations(){
        rv.play();
    }




}

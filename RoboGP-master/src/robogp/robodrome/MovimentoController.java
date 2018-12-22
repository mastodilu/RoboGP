package robogp.robodrome;

import robogp.deck.InstructionCard;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author MatteoDiLucchio
 */
public class MovimentoController {
    
    /**
     * Istanza del robodromo.
     */
    Robodrome rb;
    /**
     * Istanza del robot.
     */
    RobotMarker rm;
    /**
     * Istanza della scheda istruzione.
     */
    InstructionCard ic;
    
    /**
     * Costruttore parametrico.
     * @param rb istanza del robodromo.
     */
    public MovimentoController(Robodrome rb){
        this.rb = rb;
    }
    
    /**
     * Controlla passo per passo che il movimento sia ammissibile.
     * @param ic la scheda istruzione dal quale leggere la mossa
     * @param rm il robot da muovere
     */
    public void addRobotMove(InstructionCard ic, RobotMarker rm){
        this.ic = ic;
        this.rm = rm;
        if(movimentoAmmissibile()){
            eseguiMovimento();
        }
    }
    
    
    /**
     * Controlla che il robot possa avanzare di una casella.
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(){
        if(bloccatoDaMuri())            return false;
        if(robotInCellaSuccessiva())    return false;
        return true;
    }

    /**
     * Aggiorna le variabili di posizione e direzione del robot.
     */
    private void eseguiMovimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Controlla la presenza di muri nella cella corrente e nella cella successiva
     * rispetto alla direzione del movimento.
     * @return true se ci sono muri che bloccano,
     *      false se il passaggio e' libero e ci si puo' muovere
     */
    private boolean bloccatoDaMuri() {
        Direction d = this.rm.getDirection();
        Direction opposta = direzioneOpposta(d);
        int riga, colonna; //riga e colonna correnti del robot
            riga = this.rm.getRiga();
            colonna = this.rm.getColonna();
        BoardCell corrente, successiva;
            corrente = rb.getCell(riga, colonna);
            successiva = cellaSuccessiva(riga, colonna, d);
        
        //controlla muro in questa cella
        if(corrente.hasWall(d))     return true;
        
        //controlla muri nella cella successiva
        if(successiva.hasWall(opposta))     return true;
        
        return false;
    }

    /**
     * Restituisce la cella successiva del robodromo rispetto alla posizione
     * e alla direzione corrente del robot.
     * @param riga riga corrente
     * @param colonna colonna corrente
     * @param d direzione corrente
     * @return la cella successiva se esistente, altrimenti null
     */
    private BoardCell cellaSuccessiva(int riga, int colonna, Direction d) {
        try{
            if(d == Direction.W)        return this.rb.getCell(riga, colonna--);
            else if(d == Direction.N)   return this.rb.getCell(riga++, colonna);
            else if(d == Direction.E)   return this.rb.getCell(riga, colonna++);
            else                        return this.rb.getCell(riga--, colonna);
        }catch(NullPointerException e){
            //se la cella successiva esce dalla mappa
            return null;
        }
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
     * Controlla che non ci siano robot nella cella successiva.
     * @return true se ci sono robot nella cella successiva,
     *      false altrimenti
     */
    private boolean robotInCellaSuccessiva() {
        Direction d = this.rm.getDirection();
        int riga, colonna; //riga e colonna correnti del robot
            riga = this.rm.getRiga();
            colonna = this.rm.getColonna();
        BoardCell successiva;
            successiva = cellaSuccessiva(riga, colonna, d);
        
        return successiva.hasRobot();
    }

}

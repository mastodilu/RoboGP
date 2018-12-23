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
     * Istanza della view del robodromo.
     */
    RobodromeView rv;

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
     * Riga corrente del robot.
     */
    int rigaCorrente;
 
    /**
     * Colonna corrente del robot.
     */
    int colonnaCorrente;
    
    /**
     * Direzione corrente del robot.
     */
    Direction direzioneCorrente;
    
    /**
     * Cella corrente del robot.
     */
    BoardCell cellaCorrente;
    
    /**
     * Costruttore parametrico.
     * @param rb istanza del robodromo.
     */
    public MovimentoController(RobodromeView rv){
        this.rv = rv; // RobodromeView
        this.rb = rv.getDrome(); // Robodrome
    }
    
    /**
     * Inizializza le variabili coi valori correnti del robot
     * passato come parametro.
     */
    private void initVariabili(){
        rigaCorrente = this.rm.getRiga();
        colonnaCorrente = this.rm.getColonna();
        cellaCorrente = this.rb.getCell(rigaCorrente, colonnaCorrente);
        direzioneCorrente = this.rm.getDirection();
    }
    
    /**
     * Muove il robot finche' il movimento e' ammissibile.
     * @param ic la scheda istruzione dal quale leggere la mossa
     * @param rm il robot da muovere
     */
    public RobotMarker muoviRobot(InstructionCard ic, RobotMarker rm){
        this.ic = ic;
        this.rm = rm;
        initVariabili();
        BoardCell cellaSuccessiva;
        int movimento = ic.getMovimento();//di quanto dovrebbe muoversi
        int passiEffettuati;
        boolean backup = ic.getTipo().equals("backup");//se ic e' backup card deve gestire il movimento all'indietro
        
        cellaCorrente.robotOutside();
        
        //inverte MOMENTANEAMENTE la direzione del robot in modo da farlo camminare in avanti
        //per sfruttare il metodo 'movimentoAmmissibile()'
        if(backup)  direzioneCorrente = direzioneOpposta(direzioneCorrente);
        
        //movimentoAmmissibile controlla di poter avanzare di una casella in avanti
        while(movimento > 0 && movimentoAmmissibile()){
            System.out.println("Movimento ammissibile");
            movimento--;
            cellaSuccessiva = this.cellaSuccessiva();
            if(cellaSuccessiva == null)
                break;
            cellaCorrente = cellaSuccessiva;
            this.rigaCorrente = cellaCorrente.getRiga();
            this.colonnaCorrente = cellaCorrente.getColonna();
        }
        passiEffettuati = ic.getMovimento() - movimento;
        
        //esegue l'animazione del movimento
        this.rv.addRobotMove(rm, passiEffettuati, direzioneCorrente, ic.getRotazione());
        rv.play();
        
        //ripristina la direzione originale del robot
        if(backup)  direzioneCorrente = direzioneOpposta(direzioneCorrente);

        aggiornaVariabiliRobot(ic.getRotazione());
        System.out.println("Mosso di " + passiEffettuati + " passi verso "
                + direzioneCorrente);
        
        return this.rm;
    }
    
    
    /**
     * Aggiorna le variabili del robot dopo che e' stato compiuto tutto il movimento.
     * @param finale la cella finale raggiunta dal robot
     * @param direzioneFinale
     */
    private void aggiornaVariabiliRobot(Rotation rotazione){
        //robot
        rm.setPosizione(rigaCorrente, colonnaCorrente);
        rm.setDirection(Rotation.changeDirection(direzioneCorrente, rotazione));
        rm.updateStoricoPosizioni(rigaCorrente, colonnaCorrente);
        rm.updateStoricoDirezioni(direzioneCorrente);
        
        //cella
        this.rb.getCell(rigaCorrente, colonnaCorrente).robotInside();
    }
    
    
    /**
     * Controlla che il robot possa avanzare di una casella.
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(){
        if(bloccatoDaBordi())           return false;
        if(bloccatoDaMuri())            return false;
        if(robotInCellaSuccessiva())    return false;
        return true;
    }
    
    
    /**
     * Controlla che il movimento rispetto alla posizione corrente
     * non sia bloccato da bordi.
     * @return true se gli indici escono dalla mappa, false altrimenti.
     */
    private boolean bloccatoDaBordi(){
        if(direzioneCorrente == Direction.W && colonnaCorrente == 0)
            return true;
        else if(direzioneCorrente == Direction.N && rigaCorrente == 0)
            return true;
        else if(direzioneCorrente == Direction.E && colonnaCorrente == rb.getColumnCount()-1)
            return true;
        else if(direzioneCorrente == Direction.S && rigaCorrente == rb.getRowCount()-1)
            return true;
        return false;
    }


    /**
     * Controlla la presenza di muri nella cella corrente e nella cella successiva
     * rispetto alla direzione del movimento.
     * @return true se ci sono muri che bloccano,
     *      false se il passaggio e' libero e ci si puo' muovere
     */
    private boolean bloccatoDaMuri() {
        Direction opposta = direzioneOpposta(direzioneCorrente);
        BoardCell successiva = cellaSuccessiva();
        
        //controlla muro in questa cella
        if(cellaCorrente.hasWall(direzioneCorrente))     return true;
        //controlla muri nella cella successiva
        if(successiva == null || successiva.hasWall(opposta))
            return true;

        return false;
    }
    
    /**
     * Restituisce la cella successiva del robodromo rispetto alla posizione
     * e alla direzione corrente del robot.
     * @return la cella successiva
     */
    private BoardCell cellaSuccessiva() {
        if(direzioneCorrente == Direction.W)        return this.rb.getCell(rigaCorrente, colonnaCorrente-1);
        else if(direzioneCorrente == Direction.N)   return this.rb.getCell(rigaCorrente-1, colonnaCorrente);
        else if(direzioneCorrente == Direction.E)   return this.rb.getCell(rigaCorrente, colonnaCorrente+1);
        else                                        return this.rb.getCell(rigaCorrente+1, colonnaCorrente);
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
        return cellaSuccessiva().hasRobot();
    }
    
    
    
    /**
     * Piazza il robot in una posizione iniziale specificata a mano.
     * @param rm robot
     * @param d direzione
     * @param r riga
     * @param c colonna
     */
    public void placeRobot(RobotMarker rm, Direction d, int r, int c){
        rv.placeRobot(rm, d, r, c, true); // aggiunge il robot al tabellone        
        rm.setDirection(d);
        rm.setPosizione(r, c);
        rm.updateStoricoDirezioni(d);
        rm.updateStoricoPosizioni(r, c);
    }

}

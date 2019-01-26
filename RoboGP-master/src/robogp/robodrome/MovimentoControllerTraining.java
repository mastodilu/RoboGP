package robogp.robodrome;

import java.util.ArrayList;
import robogp.deck.InstructionCard;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author MatteoDiLucchio
 */
public class MovimentoControllerTraining {
    
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
     * Direzione corrente del robot.
     */
    Direction direzioneCorrente;
    
    /**
     * Cella corrente del robot.
     */
    BoardCell cellaCorrente;
    
    
    /**
     * Array di robot nella mappa
     */
    ArrayList<RobotMarker> arrayRobot;
    
    /**
     * Costruttore parametrico.
     * @param rb istanza del robodromo.
     */
    public MovimentoControllerTraining(RobodromeView rv, ArrayList<RobotMarker> arrayRobot){
        this.rv = rv; // RobodromeView
        this.rb = rv.getDrome(); // Robodrome
        this.arrayRobot = arrayRobot;
    }
    
    /**
     * Inizializza le variabili coi valori correnti del robot
     * passato come parametro.
     */
    private void initVariabili(InstructionCard ic, RobotMarker rm){
        this.ic = ic;
        this.rm = rm;
        cellaCorrente = this.rb.getCell(this.rm.getLastPosition().getRiga(), this.rm.getLastPosition().getColonna());
        direzioneCorrente = this.rm.getLastDirection();
    }
    
    /**
     * Muove il robot finche' il movimento e' ammissibile.
     * @param ic la scheda istruzione dal quale leggere la mossa
     * @param rm il robot da muovere
     */
    public void muoviRobot(InstructionCard ic, RobotMarker rm){
        initVariabili(ic, rm);
        BoardCell cellaSuccessiva;
        int movimento = ic.getMovimento();//di quanto dovrebbe muoversi
        int passiEffettuati;
        boolean backup = ic.getTipo().equals("backup");//se ic e' backup card deve gestire il movimento all'indietro
        boolean bucoNero = this.bucoNero(cellaCorrente);
        
        
        cellaCorrente.robotOutside();
        
        //inverte MOMENTANEAMENTE la direzione del robot in modo da farlo camminare in avanti
        //per sfruttare il metodo 'movimentoAmmissibile()'
        if(backup)  direzioneCorrente = direzioneOpposta(direzioneCorrente);
        
        //movimentoAmmissibile controlla di poter avanzare di una casella in avanti
        while(!bucoNero && movimento > 0 && movimentoAmmissibile()){
            System.out.println("Movimento ammissibile");
            movimento--;
            cellaSuccessiva = this.cellaSuccessiva();
            if(cellaSuccessiva == null)
                break;
            cellaCorrente = cellaSuccessiva;
            bucoNero = this.bucoNero(cellaCorrente);
        }
        passiEffettuati = ic.getMovimento() - movimento;
        
        //aggiunge (senza eseguire) l'animazione del movimento
        this.rv.addRobotMove(rm, passiEffettuati, direzioneCorrente, ic.getRotazione());
        //l'animazione va eseguita chiamando il metodo play() quando tutte le istruzioni sono state aggiunte
        
        //ripristina la direzione originale del robot
        if(backup)  direzioneCorrente = direzioneOpposta(direzioneCorrente);
        
        direzioneCorrente = Rotation.changeDirection(direzioneCorrente, ic.getRotazione());
        aggiornaVariabiliRobot(this.cellaCorrente, direzioneCorrente);
        
        System.out.println("Mosso di " + passiEffettuati + " passi verso "
                + direzioneCorrente);
    }
    
    
    /**
     * Avvia l'esecuzione delle animazioni
     */
    public void play(){
        rv.play();
    }
    
    
    /**
     * Aggiorna le variabili del robot dopo che e' stato compiuto tutto il movimento.
     * @param finale la cella finale raggiunta dal robot
     * @param direzioneFinale
     */
    private void aggiornaVariabiliRobot(BoardCell cella, Direction direzione){
        rm.updatePosizione(cella.getRiga(), cella.getColonna(), direzione);
        cella.robotInside();
    }
    
    
    /**
     * Controlla che il robot possa avanzare di una casella.
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(){
        if(bloccatoDaBordi())           return false;
        if(bloccatoDaMuri())            return false;
        return true;
    }
    
    
    /**
     * Controlla che il movimento rispetto alla posizione corrente
     * non sia bloccato da bordi.
     * @return true se gli indici escono dalla mappa, false altrimenti.
     */
    private boolean bloccatoDaBordi(){
        int riga = this.cellaCorrente.getRiga();
        int colonna = this.cellaCorrente.getColonna();
        if(direzioneCorrente == Direction.W && colonna == 0)
            return true;
        else if(direzioneCorrente == Direction.N && riga == 0)
            return true;
        else if(direzioneCorrente == Direction.E && colonna == rb.getColumnCount()-1)
            return true;
        else if(direzioneCorrente == Direction.S && riga == rb.getRowCount()-1)
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
        int riga = this.cellaCorrente.getRiga();
        int colonna = this.cellaCorrente.getColonna();
        if(direzioneCorrente == Direction.W)        return this.rb.getCell(riga, colonna-1);
        else if(direzioneCorrente == Direction.N)   return this.rb.getCell(riga-1, colonna);
        else if(direzioneCorrente == Direction.E)   return this.rb.getCell(riga, colonna+1);
        else                                        return this.rb.getCell(riga+1, colonna);
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
     * @return il robot nella cella successiva a quello corrente, altrimenti null
     */
    public RobotMarker robotInCellaSuccessiva() {
        BoardCell next = cellaSuccessiva();
        boolean flag = next.hasRobot();
        
        System.out.printf("Cella successiva has robot: %b\n", flag);
        
        if(flag){
            int nextR, nextC, r, c;
            nextR = next.getRiga();
            nextC = next.getColonna();
            for(RobotMarker robotmarker : this.arrayRobot){
                    r = robotmarker.getLastPosition().getRiga();
                    c = robotmarker.getLastPosition().getColonna();
                if(nextR == r && nextC == c)
                    return robotmarker;
            }
        }
        return null;
    }
    
    
    
    /**
     * Piazza il robot in una posizione iniziale specificata a mano.
     * @param rm robot
     * @param d direzione
     * @param r riga
     * @param c colonna
     */
    public void placeRobot(RobotMarker rm, Direction d, int r, int c){
        BoardCell cella = this.rb.getCell(r, c);
        if(cella != null){
            rv.placeRobot(rm, d, r, c, true); // aggiunge il robot al tabellone
            cella.robotInside();
            rm.updatePosizione(r, c, d);
        }
    }
    
    
    /**
     * Avvia i nastri trasportatori semplici o express se il robot è posizionato
     * sopra ad uno di essi.
     * @param rb robot
     */
    public void nastriTrasportatori(){
        for(RobotMarker rm : this.arrayRobot){
            initVariabili(null, rm);

            if(this.cellaCorrente.getType() == 'B'){
                nastroTrasportatoreSemplice();
            }
            else {
                nastroTrasportatoreExpress();
            }
        }
    }
    
    
    /**
     * Muove il robot di una posizione nella direzione del nastro trasportatore.
     */
    private void nastroTrasportatoreSemplice(){
        BeltCell beltcell = (BeltCell)this.rb.getCell(this.cellaCorrente.getRiga(), this.cellaCorrente.getColonna());
        direzioneCorrente = beltcell.getOutputDirection();
        if(movimentoAmmissibile()){
            this.rv.addRobotMove(rm, 1, direzioneCorrente, Rotation.NO);
            this.cellaCorrente.robotOutside();
            this.cellaCorrente = cellaSuccessiva();
            aggiornaVariabiliRobot(cellaCorrente, rm.getLastDirection());
        }
    }
    
    
    /**
     * Muove il robot di una posizione nella direzione del nastro trasportatore.
     */
    private void nastroTrasportatoreExpress(){
        int riga, colonna;
            riga = this.cellaCorrente.getRiga();
            colonna = this.cellaCorrente.getColonna();
        BeltCell beltcell;
        for(int i = 0; i < 2; i++){
            if(this.rb.getCell(riga, colonna).getType() == 'E'){
                beltcell = (BeltCell)this.rb.getCell(riga, colonna);
                direzioneCorrente = beltcell.getOutputDirection();
                if(movimentoAmmissibile()){
                    this.cellaCorrente.robotOutside();
                    this.cellaCorrente = cellaSuccessiva();
                    riga = cellaCorrente.getRiga();
                    colonna = this.cellaCorrente.getColonna();
                    this.rv.addRobotMove(rm, 1, direzioneCorrente, Rotation.NO);
                    aggiornaVariabiliRobot(cellaCorrente, rm.getLastDirection());
                }
            }
        }
    }
    
    
    /**
     * @return true se la cella corrente è un buco nero,
     *  false altrimenti
     */
    private boolean bucoNero(BoardCell cella){
        return cella.getType() == 'P';
    }
    
}
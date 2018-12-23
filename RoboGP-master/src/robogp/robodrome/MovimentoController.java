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
     * Costruttore parametrico.
     * @param rb istanza del robodromo.
     */
    public MovimentoController(RobodromeView rv){
        this.rv = rv;
        this.rb = rv.getDrome();
    }
    
    /**
     * Muove il robot finche' il movimento e' ammissibile.
     * @param ic la scheda istruzione dal quale leggere la mossa
     * @param rm il robot da muovere
     */
    public void addRobotMove(InstructionCard ic, RobotMarker rm){
        this.ic = ic;
        this.rm = rm;
        
        int movimento = ic.getMovimento();
        int passi = 0;//conta quanti passi puo' effettuare il robot
        Rotation rotazione = ic.getRotazione();
        boolean backup = ic.getTipo().equals("backup");//indietro di un passo
        BoardCell iniziale, finale;
            iniziale = rb.getCell(rm.getRiga(), rm.getColonna());
        
        //inverte MOMENTANEAMENTE la direzione del robot in modo da farlo camminare in avanti
        //per sfruttare il metodo 'movimentoAmmissibile()'
        if(backup)  this.rm.setDirection(this.direzioneOpposta(this.rm.getDirection()));
        
        //movimentoAmmissibile controlla di poter avanzare di una casella in avanti
        while(movimento > 0 && movimentoAmmissibile()){
            movimento--;
            passi++;
        }
        
        //ripristina la direzione originale del robot
        if(backup)  this.rm.setDirection(this.direzioneOpposta(this.rm.getDirection()));
        
        //esegue l'animazione del movimento
        this.rv.addRobotMove(rm, passi, rm.getDirection(), rotazione);
        
        //calcola la cella raggiunta dopo n passi
        finale = cellaFinale(iniziale, rm.getDirection(), passi);
        
        aggiornaVariabili(finale, rm.getDirection());
    }
    
    
    /**
     * Aggiorna le variabili del robot dopo che e' stato compiuto tutto il movimento.
     * @param finale la cella finale raggiunta dal robot
     * @param direzioneFinale
     */
    private void aggiornaVariabili( BoardCell finale,
                                             Direction direzioneFinale){
        int rigaFinale = finale.getRiga();
        int colonnaFinale = finale.getColonna();
        
        //robot
        rm.setPosizione(rigaFinale, colonnaFinale);
        rm.setDirection(direzioneFinale);
        rm.updateStoricoPosizioni(rigaFinale, colonnaFinale);
        rm.updateStoricoDirezioni(direzioneFinale);
        
        //cella
        finale.setRobot();
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
        if(successiva == null || successiva.hasWall(opposta))
            return true;
        
        return false;
    }
    
    
    /**
     * Restituisce la cella raggiunta dopo tot passi.
     * @param iniziale cella iniziale
     * @param dir direziona seguita
     * @param passi di quante celle ci si vuole muovere
     * @return la cella finale dopo tot passi
     */
    private BoardCell cellaFinale(BoardCell iniziale, Direction dir, int passi){
        BoardCell temp = iniziale, finale = iniziale;
        
        if(passi<0) passi = -passi;
        
        while(temp != null && passi > 0){
            finale = temp;
            temp = cellaSuccessiva(iniziale.getRiga(), iniziale.getColonna(), dir);
            passi--;
        }
        
        return finale;
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

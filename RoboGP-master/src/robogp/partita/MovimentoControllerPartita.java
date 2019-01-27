package robogp.partita;

import java.util.ArrayList;
import robogp.deck.InstructionCard;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BeltCell;
import robogp.robodrome.BoardCell;
import robogp.robodrome.Direction;
import robogp.robodrome.FloorCell;
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
     * Robot da spingere.
     */
    RobotMarker spingi;
    
    /**
     * Pattern singleton.
     */
    private static MovimentoControllerPartita singleInstance;
    
    
    /**
     * Costruttore.
     */
    private MovimentoControllerPartita(){
        spingi = null;
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
    public static MovimentoControllerPartita getInstance(){
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
    public void initialized() throws Exception{
        if( this.robodrome == null
            || this.robots == null
            || this.rv == null){
            throw new Exception("Istanza non inizializzata correttamente");
        }
    }
    
    
    
    /**
     * Controlla che il robot possa avanzare di una casella,
     * ma non controlla la presenza di robot nella cella successiva.
     * @param cella dalla quale ci si muove
     * @param direzione del movimento
     * @return true se non ci sono ostacoli, false altrimenti.
     */
    public boolean movimentoAmmissibile(BoardCell cella, Direction direzione){
        return possoMuovermi(cella, direzione);
    }
    
    
    /**
     * Restituisce true quando non ci sono ostacoli che impediscono il movimento,
     * ma non controlla la presenza di robot nella cella successiva.
     * @param cella di partenza del movimento
     * @param direzione del movimento
     * @return true quando il movimento e' ostacolato, false altrimenti.
     */
    private boolean possoMuovermi(BoardCell cella, Direction direzione){
        if(this.bucoNero(cella))                return false;
        if(bloccatoDaBordi(cella, direzione))   return false;
        if(bloccatoDaMuri(cella, direzione))    return false;
        return true;
    }
    
    
    /**
     * Esegue l'istruzione passata.
     * @param robot che esegue l'istruzione
     * @param istruzione da eseguire
     */
    public void eseguiIstruzione(RobotMarker robot, InstructionCard istruzione){
        try{
            initialized(); // controlla che le variabili siano inizializzate
            BoardCell cellaIniziale = robodrome.getCell(
                robot.getLastPosition().getRiga(), 
                robot.getLastPosition().getColonna()
            );
            Rotation rotazione = istruzione.getRotazione();
            int distanza = istruzione.getMovimento(); // distanza da percorrere
            Direction direzioneRobot = robot.getLastDirection();//direzione corrente del robot
            
            boolean backup = istruzione.getTipo().equalsIgnoreCase("backup"); // se l'istruzione e' "backup"
            
            //gestisce movimento indietro come se fosse in avanti
            if(backup)      direzioneRobot = direzioneOpposta(direzioneRobot);
            
            if(distanza > 0)
                faiUnPasso(robot, distanza, direzioneRobot);
            else if(rotazione != null)
                muovi(robot, 0, direzioneRobot, rotazione);
            
            System.out.println("MCP esegui istruzione");
        }catch(Exception e){ System.err.println(e.getMessage()); }
    }
    
    
    
    /**
     * Esegue l'animazione della caduta nel buco nero.
     * @param robot che cade
     */
    private void precipita(RobotMarker robot){
        rv.addRobotFall(robot);
        
        //i buchi neri non possono contenere robot
        this.robodrome.getCell(
                robot.getLastPosition().getRiga(),
                robot.getLastPosition().getColonna()
        ).robotOutside();
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
     * @return il numero passi compiuti.
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
            else break;
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
     * Controlla per ogni robot se va eseguito lo spostamento dovuto al nastro semplice.
     */
    public void nastriTrasportatoriSemplici(){
        for(RobotMarker robot : robots){
            nastroSemplice(robot);
        }
    }
    
    
    /**
     * Se il robot si trova su un nastro trasportatore semplice
     * lo fa slittare di una posizione nella direzione del nastro se
     * il movimento non e' ostacolato.
     * @param robot da spostare
     */
    private void nastroSemplice(RobotMarker robot){
        int riga, colonna;
            riga = robot.getLastPosition().getRiga();
            colonna = robot.getLastPosition().getColonna();
        BoardCell cella = this.robodrome.getCell(riga, colonna);
        if(cella.getType() == 'B'){ // nastro trasportatore semplice
            BeltCell belt = (BeltCell)cella;
            Direction direzione = belt.getOutputDirection();
            this.faiUnPasso(robot, 1, direzione);
        }
    }
    
    
    
    /**
     * Controlla per ogni robot se va eseguito lo spostamento dovuto al nastro express.
     */
    public void nastriTrasportatoriExpress(){
        for(RobotMarker robot : robots){
            nastroExpress(robot);
        }
    }
    
    
    /**
     * Muove il robot se si trova su un nastro express.
     * @param robot da muovere
     */
    private void nastroExpress(RobotMarker robot){
        int riga, colonna;
            riga = robot.getLastPosition().getRiga();
            colonna = robot.getLastPosition().getColonna();
        BoardCell cella = robodrome.getCell(riga, colonna);
        int contaAttivazioni = 0; // conta le attivazioni eseguite
        int numeroAttivazioni = 2; // quante attivazioni del nastro si verificano
        
        // while cella e' nastro express e devono attivarsi i nastri express
        while(  cella.getType() == 'E' && contaAttivazioni < numeroAttivazioni){
            BeltCell belt = (BeltCell)cella;
            Direction direzione = belt.getOutputDirection();
            
            if(faiUnPasso(robot, 1, direzione)){
                contaAttivazioni++;
                cella = this.cellaSuccessiva(cella, direzione); //ripete il ciclo con la cella successiva
            }else{
                //se ci sono ostacoli non deve ripetere il ciclo una seconda volta
                break;
            }
        }
    }
    
    
    
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
        Direction nuovaDirezione;
        
        // cella corrente del robot
        cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(), 
                robot.getLastPosition().getColonna()
        );
        raggiunta = cellaRaggiunta(cella, direzione, passi);

        //aggiorna variabili celle
        cella.robotOutside();
        raggiunta.robotInside();
        nuovaDirezione = Rotation.changeDirection(robot.getLastDirection(), rotazione);
        robot.updatePosizione(raggiunta.getRiga(), raggiunta.getColonna(), nuovaDirezione);
        //animazione movimento
        this.rv.addRobotMove(robot, passi, direzione, rotazione);
    }
    
    
    
    /**
     * Controlla tutti i robot e si blocca quando
     * trova quello nella cella passata come parametro.
     * @param cella che potrebbe contenere un robot
     * @return il robot nella cella specificata, null altrimenti
     */
    private RobotMarker getRobotInCell(BoardCell cella){
        for(RobotMarker robot : this.robots){
            int riga, colonna;
                riga = robot.getLastPosition().getRiga();
                colonna = robot.getLastPosition().getColonna();
            if(riga == cella.getRiga()
                    && colonna == cella.getColonna())
                return robot;
        }
        return null;
    }
    
    
    
    
    
    /**
     * Controlla se rispetto alla cella passata e a quella direzione del movimento
     * e' possibile spingere il robot incontrato.
     * @param prima cella di riferimento
     * @param direzione del movimento
     * @return true se c'e' un robot da spingere che puo' essere spinto
     * nella cella successiva a quella specificata, false altrimenti.
     * Restituisce false anche se non c'e' un robot nella cella successiva.
     */
    private boolean devoSpingere(BoardCell prima, Direction direzione){
        BoardCell successiva = cellaSuccessiva(prima, direzione);
        
        if(successiva.hasRobot() == false)                  return false;
        
        RobotMarker robotDaSpingere = this.getRobotInCell(successiva);
        if(robotDaSpingere == null)                         return false;
        
        //true se c'e' un robot da spingere e lo si puo' spingere
        if(movimentoAmmissibile(successiva, direzione))     return true;
        
        return false;
    }
    
    
    
    
    /**
     * Avanza di un passo e spinge gli eventuali robot da spingere se e' possibile.
     * @param robot che si muove
     * @param passi da compiere
     * @param direzione del movimento
     * @return true se si muove, false altrimenti.
     */
    private boolean faiUnPasso(RobotMarker robot, int passi, Direction direzione){
        BoardCell cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(),
                robot.getLastPosition().getColonna()
        );
        
        if(bucoNero(cella)){
            precipita(robot);
        }
            
        if(passi > 0){ //se devo camminare
            if(possoMuovermi(cella, direzione) ){ // e non ci sono ostacoli
                BoardCell successiva = cellaSuccessiva(cella, direzione);
                //se c'e' un robot davanti
                if(successiva.hasRobot()){
                    RobotMarker daSpingere = getRobotInCell(successiva);
                    //true se il robot e' stato spinto, false altrimenti
                    boolean hoSpinto = faiUnPasso(daSpingere, 1, direzione);
                    if(!hoSpinto){
                        //non ha potuto spingere quindi si muove di 0 e termina
                        passi = 0;
                        muovi(robot, 0, direzione, Rotation.NO);//animazione, variabili
                        return false;
                    }else{
                        // ha spinto, quindi si muove di 1 e termina
                        passi = 1;
                        muovi(robot, 1, direzione, Rotation.NO);
                        return true;
                    }
                }

                muovi(robot, 1, direzione, Rotation.NO);//animazione, variabili
                return faiUnPasso(robot, passi-1, direzione);
            }
            else{
                muovi(robot, 0, direzione, Rotation.NO);//animazione, variabili
                return false;
            }
                
        }
        return true;
    }
    

    /**
     * Posiziona il robot sul dock assegnato.
     * @param robot da posizionare
     */
    public void placeOnDock(RobotMarker robot){
        int riga, colonna;
        FloorCell dock = this.robodrome.getDock(robot.getDock());
        riga = dock.getRiga();
        colonna = dock.getColonna();
        placeRobot(robot, Direction.E, riga, colonna);
    }
    
    
    
    /**
     * Avvia l'esecuzione delle animazioni.
     */
    public void playAnimations(){
        rv.play();
    }




}
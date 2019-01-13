package robogp.matchmanager;

import java.util.ArrayList;
import robogp.Giocatore.Giocatore;
import robogp.Giocatore.Robot.RobotMarkerPlaying;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author MatteoDiLucchio
 */
public class StatoPartita {
    
    /**
     * Giocatori in partita.
     */
    private ArrayList<Giocatore> giocatori;
    
    /**
     * Condizione di fine partita:
     *      1: il primo giocatore
     *      2: i primi tre giocatori
     *      3: tutti tranne l'ultimo giocatore.
     */
    private  int condizioneFinePartita;
    
    /**
     * Numero massimo di giocatori.
     */
    private int numeroMaxGiocatori;
    
    /**
     * Il numero di robot per giocatore.
     */
    private int numRobotPerGiocatore;
    
    /**
     * True se i giocatori hanno un upgrade iniziale, false altrimenti.
     */
    private boolean upgradeIniziali;
    
    /**
     * Robodromo della partita.
     */
    protected Robodrome robodrome;
    
    /**
     * View del robodromo.
     */
    protected RobodromeView robodromeview;
    
    
    
    
    
    
    
    
    
    
    // COSTRUTTORI
    
    public StatoPartita(
            int finePartita,
            ArrayList<Giocatore> giocatori,
            int robotPerGioc,
            int numGioc,
            Robodrome robodrome,
            RobodromeView view,
            boolean upg){
        this.condizioneFinePartita = finePartita;
        this.giocatori = giocatori;
        this.numRobotPerGiocatore = robotPerGioc;
        this.numeroMaxGiocatori = numGioc;
        this.robodrome = robodrome;
        this.robodromeview = view;
        this.upgradeIniziali = upg;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Aggiunge un giocatore all'elenco di giocatori e gli assegna il robot.
     * Se nickname esiste già allora il robot viene assegnato al giocatore trovato,
     * altrimenti viene creato un nuovo giocatore a cui assegnare il robot.
     * @param nickname nome del giocatore
     * @param robot robot da assegnare
     */
    protected void addPlayer(String nickname, RobotMarkerPlaying robot){
        //se nickname non è nella lista di giocatori
        //crea nuovo giocatore e assegna robot
        //altrimenti assgna robot to giocatore
        boolean assegnato = false;
        for(Giocatore g : this.giocatori){
            if(g.getNickname().equalsIgnoreCase(nickname)){
                g.assegnaRobot(robot);
                assegnato = true;
                break;
            }
        }
        if(!assegnato)
            this.giocatori.add(new Giocatore(nickname, robot));
    }
}

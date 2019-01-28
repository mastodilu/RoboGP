package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BoardCell;
import robogp.robodrome.FloorCell;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;


/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class MappaController {
    
    /**
     * Array di robot in partita.
     */
    private ArrayList<RobotMarker> robots;
    
    /**
     * View del robodromo.
     */
    private RobodromeView rv;
    
    /**
     * Robodromo della partita.
     */
    private Robodrome robodrome;
    
    /**
     * Pattern singleton.
     */
    public static MappaController singleInstance;
    
    
    /**
     * Inizializza l'istanza di MappaController.
     * @param r robodromo
     * @param rv view del robodromo
     * @param robots arraylist di robot in partita
     */
    public void init(Robodrome r, RobodromeView rv, ArrayList<RobotMarker> robots){
        this.robodrome = r;
        this.rv = rv;
        this.robots = robots;
    }
    
    
    
    /**
     * Controlla che l'istanza sia inizializzata e pronta per essere usata.
     * @return true se ogni variabile e' inizializzata, false altrimenti.
     */
    public boolean checkInit(){
        return this.robodrome != null
                && this.rv != null
                && this.robots != null;
    }
    
    /**
     * Costruttore
     */
    private MappaController(){
    }
    
    
    /**
     * @return l'istanza di mappa controller.
     */
    public static MappaController getInstance(){
        if(singleInstance == null)
            singleInstance = new MappaController();
        return singleInstance;
    }
    
    
    
    //TODO
    //CHECKPOINT (chiave e bandiera) floorcell metodo isCheckpoint
    //PUNTI RIPARAZIONE (chiave inglese) floorcell metodo isRepair
    
    /**
     * Restituisce true se il robot e' posizionato sulla cella di upgrade.
     * @param robot di riferimento.
     * @return true se deve ricevere un upgrade, false altrimenti.
     */
    public boolean puoRicevereUpgrade(RobotMarker robot){
        BoardCell cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(),
                robot.getLastPosition().getColonna()
        );
        return cella.getType() == 'F' && ((FloorCell)cella).isUpgrade();
    }
    
    
    /**
     * @param robot consinderato
     * @return true quando il robot e' sul successivo checkpoint da toccare,
     * false altrimenti.
     */
    public boolean toccatoCheckpoint(RobotMarker robot){
        BoardCell cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(),
                robot.getLastPosition().getColonna()
        );
        if(cella.getType() == 'F' && ((FloorCell)cella).isCheckpoint()){
            int toccato = ((FloorCell)cella).getCheckpoint();
            int precedente = robot.getCheckpoint();
            return toccato == precedente+1;
        }
        return false;
    }
}

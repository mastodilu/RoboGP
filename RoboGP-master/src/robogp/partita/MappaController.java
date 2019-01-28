package robogp.partita;

import java.util.ArrayList;
import robogp.matchmanager.RobotMarker;
import robogp.robodrome.BoardCell;
import robogp.robodrome.Direction;
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
    
    

    /**
     * 
     * @param robot di riferimento
     * @return true quando il robot e' su una cella riparazione,
     * false altrimenti.
     */
    public boolean toccatoPuntoRiparazione(RobotMarker robot){
        BoardCell cella = this.robodrome.getCell(
                robot.getLastPosition().getRiga(),
                robot.getLastPosition().getColonna()
        );
        return cella.getType() == 'F' && ((FloorCell)cella).isRepair();
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
}

package robogp.Training;

import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.robodrome.Robodrome;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class TrainingController {
    
    private static TrainingController singleInstance;
//    TODO
    private final Training theTraining;
//    private InstructionCard[] programma = null;
    private Robodrome theRobodrome = null;
    private RobotMarkerTraining robot = null;
    private String nome_utente = null;
    
    
    
    //costruttore
    private TrainingController(){
        theTraining = new Training();
        //TODO setta le instruction cards
        System.out.println("creato training controller.");
    }
    
    public void init(String nome_utente, String robodromo){
        String rbdFileName = "robodromes/" + robodromo + ".txt";
        theRobodrome = new Robodrome(rbdFileName);
        robot = new RobotMarkerTraining('e', "robot-emerald", "red", "default", -1);
        this.nome_utente = nome_utente;
        doSomething();
    }

    public static TrainingController getInstance(){
        System.out.println("sono training controller");
        if( singleInstance == null )
            singleInstance = new TrainingController();
        return singleInstance;
    }
    
    //stampo cose a caso giusto per vedere se le cose funzionano senza sporcare il codice
    private void doSomething(){
        System.out.println("Robodromo " + theRobodrome.getName() +
                "\nrighe e colonne, " + this.theRobodrome.getRowCount() +
                ", " + this.theRobodrome.getColumnCount());
    }
    
    public static void avvia(){
        
        System.out.println("Training controller avvia()");
    }
}

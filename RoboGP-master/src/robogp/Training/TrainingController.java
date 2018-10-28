package robogp.Training;

import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.robodrome.Robodrome;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class TrainingController {
    
    private static TrainingController singleInstance;
    private final Training theTraining;
    private Robodrome theRobodrome = null;
    private String nome_utente = null;
    
    
    
    //costruttore
    private TrainingController(){
        theTraining = new Training();
        // System.out.println("creato training controller.");
    }
    
    //inizializza l'istanza usando nome utente e nome robodromo
    public void init(String nome_utente, String robodromo){
        String rbdFileName = "robodromes/" + robodromo + ".txt";
        theRobodrome = new Robodrome(rbdFileName);
        this.nome_utente = nome_utente;
        theTraining.init(theRobodrome);
        
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
        System.out.println("utente " + nome_utente);
    }
    
    public static void avvia(){
        
        System.out.println("Training controller avvia()");
    }
}

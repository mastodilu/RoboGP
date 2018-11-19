package robogp.Training;

import robogp.robodrome.Robodrome;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class TrainingController {
    
    private static TrainingController singleInstance;
    private final Training theTraining;
    private Robodrome theRobodrome = null;    
    
    
    //costruttore
    private TrainingController(){
        theTraining = Training.getInstance();
    }
    
    //inizializza l'istanza usando nome robodromo
    public void init(String robodromo){
        final String rbdFileName = "robodromes/" + robodromo + ".txt"; // compone il filename del robodromo;

        //uso un thread per non bloccare la gui
        Thread t = new Thread() {
            public void run() {
                theRobodrome = new Robodrome(rbdFileName);
                theTraining.init(theRobodrome);
                doSomething();
            }
        };
        t.start();
        
        
    }

    //restituisce l'unica istanza di TrainingController
    public static TrainingController getInstance(){
        if( singleInstance == null )
            singleInstance = new TrainingController();
        return singleInstance;
    }
    
    //stampo cose a caso giusto per vedere se le cose funzionano senza sporcare il codice
    private void doSomething(){
        System.out.println("Robodromo " + theRobodrome.getName() +
                "\nrighe e colonne, " + this.theRobodrome.getRowCount() +
                ", " + this.theRobodrome.getColumnCount());
        System.out.println(theTraining.getDeck());
    }
    
    
    
    public static void avvia(){
        //todo implementa TrainingController.avvia()
        System.out.println("Training controller avvia()");
    }
}

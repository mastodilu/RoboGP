package robogp.Training;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class TrainingController {
    
    public enum Stati{ Created, Started, Paused, Canceled };
    private Stati stato;
    private static TrainingController singleInstance;
//    TODO
//    private final Training theTraining;
//    private InstructionCard[] programma = null;
//    private final RobotMarkerTraining robot = null;
//    private final Robodrome theRobodrome = null;
    
    
    
    //costruttore
    private TrainingController(){
        //nuovo robodromo
        //setta il robot
        //setta le instruction cards
        this.stato = Stati.Created;
        System.out.println("creato training controller.");
    }

    public static TrainingController getInstance(){
        System.out.println("sono training controller");
        if( singleInstance == null || singleInstance.stato == Stati.Canceled)
            return null;
        return singleInstance;
    }
    
    public static TrainingController getInstance(String rbName){
//        TODO usa il parametro passato per instanziare quel robodromo
        if(singleInstance == null || singleInstance.stato == Stati.Canceled)
            return null;
        return singleInstance;
    }
}

package robogp.Training;

import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.robodrome.Direction;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class Training {
    
    private RobotMarkerTraining robot = null;
    private RobodromeView robodromo = null;
    private TrainingGui trainingGui = null;
    private static Training singleInstance;
    
    //costruttore
    private Training(){
        System.out.println("Training constructor Singleton");
    }
    
    //pattern singleton
    public static Training getInstance(){
        if(Training.singleInstance == null)
            Training.singleInstance = new Training();
        return Training.singleInstance;
    }
    
    //inizializza 
    public void init(Robodrome rb){
        robodromo = new RobodromeView(rb, 35);
        robot = new RobotMarkerTraining('e', "robot-emerald", "red", "default", -1);
        robodromo.placeRobot(robot, Direction.E, 5, 1, true);
        trainingGui = TrainingGui.getInstance(robodromo);
        trainingGui.start(); //rende visibile la finestra di gioco
    };
    
}

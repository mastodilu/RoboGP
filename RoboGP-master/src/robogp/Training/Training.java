package robogp.Training;

import javax.swing.JLabel;
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
    /*
    costruttore
    @param rb istanza del robodromo
    */
    public Training(){
        System.out.println("Training constructor");
    }
    
    //inizializza 
    public void init(Robodrome rb){
        robodromo = new RobodromeView(rb, 35);
        robot = new RobotMarkerTraining('e', "robot-emerald", "red", "default", -1);
        robodromo.placeRobot(robot, Direction.E, 5, 1, true);
        trainingGui = new TrainingGui(robodromo);
        trainingGui.start(); //rende visibile la finestra di gioco
    };
    
}

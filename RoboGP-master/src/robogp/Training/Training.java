package robogp.Training;

import java.util.ArrayList;
import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.deck.*;
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
    private Deck deck = null;
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
        deck = Deck.getInstance(); // mazzo di carte
        robodromo = new RobodromeView(rb, 35); // gui del tabellone del robodromo
        robot = new RobotMarkerTraining('e', "robot-emerald", "red", "default", -1); // gui del robot nel tabellone
        robodromo.placeRobot(robot, Direction.E, 5, 1, true); // aggiunge il robot al tabellone
        trainingGui = TrainingGui.getInstance(robodromo); // crea la gui generale
        trainingGui.start(); //rende visibile la finestra di gioco
        
        buildCardSelection();
    };
    
    
    //popola il menu a tendina per la selezione delle schede istruzione della gui
    private void buildCardSelection(){
        //salvo una carta per tipo
        ArrayList<InstructionCard> cards = new ArrayList<InstructionCard>();
        //scorro il mazzo per trovare una carta per ogni tipo
        for(String tipo : deck.tipi){
            //per ogni tipo aggiunge la prima carta di quel tipo
            for(InstructionCard card : deck.getMazzo()){
                if(card.getTipo().equalsIgnoreCase(tipo)){
                    cards.add(card);
                    break; // condizione di uscita che permette di leggere solo la prima istruzione
                }
            }
        }
        trainingGui.setIstruzioni(cards);
        
    }
    
    
    public Deck getDeck(){
        return this.deck;
    }
    
}

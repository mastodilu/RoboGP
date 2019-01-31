package robogp.Training;

import java.util.ArrayList;
import robogp.deck.*;
import robogp.matchmanager.RobotMarker;
import robogp.partita.MappaController;
import robogp.partita.MovimentoControllerPartita;
import robogp.partita.RaggioController;
import robogp.robodrome.MovimentoControllerTraining;
import robogp.robodrome.Robodrome;
import robogp.robodrome.view.RobodromeView;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class Training {
    
    /**
     * istanza della GUI dell'allenamento
     */
    private TrainingGui trainingGui = null;
    
    /**
     * usato solo per creare un'istanza di carta per tipo
     */
    private Deck deck = null;
    
    /**
     * pattern singleton
     */
    private static Training singleInstance;
    
    private Training(){
        System.out.println("Training constructor Singleton");
    }
    
    //gestione pattern singleton
    public static Training getInstance(){
        if(Training.singleInstance == null)
            Training.singleInstance = new Training();
        return Training.singleInstance;
    }
    
    //inizializza
    public void init(Robodrome rb){
        deck = Deck.getInstance(); // mazzo di carte
        RobodromeView robodromo = new RobodromeView(rb, 35); // GUI del robodromo
        
        ArrayList<RobotMarker> arrayRobot = new ArrayList<RobotMarker>();
            RobotMarker robot1 = new RobotMarker("robot-emerald", "green");
            robot1.assign("GRANDE-GABO", new Integer(1));
            arrayRobot.add(robot1);
            RobotMarker robot2 = new RobotMarker("robot-red", "red");
            robot2.assign("GRANDE-GABO", new Integer(2));
            arrayRobot.add(robot2);
            RobotMarker robot3 = new RobotMarker("robot-yellow", "yellow");
            robot3.assign("GRANDE-GABO", new Integer(3));
            arrayRobot.add(robot3);
            
        MovimentoControllerPartita movimentoCtrl = MovimentoControllerPartita.getInstance();
        movimentoCtrl.init(robodromo, rb, arrayRobot);
        
        MappaController mappaCtrl = MappaController.getInstance();
        mappaCtrl.init(rb, robodromo, arrayRobot);
        
        RaggioController raggioCtrl = RaggioController.getInstance();
        raggioCtrl.init(robodromo, rb, arrayRobot, movimentoCtrl);
        
        
        trainingGui = TrainingGui.getInstance(robodromo, arrayRobot, movimentoCtrl, mappaCtrl, raggioCtrl); // crea la gui generale
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
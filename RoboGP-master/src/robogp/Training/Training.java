package robogp.Training;

import java.util.ArrayList;
import robogp.Giocatore.Robot.RobotMarkerTraining;
import robogp.deck.*;
import robogp.robodrome.MovimentoController;
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
        MovimentoController movimentoCtrl = new MovimentoController(robodromo);
        RobotMarkerTraining robot = new RobotMarkerTraining("robot-emerald", "green"); // gui del robot nel tabellone
        trainingGui = TrainingGui.getInstance(robodromo, robot, movimentoCtrl); // crea la gui generale
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

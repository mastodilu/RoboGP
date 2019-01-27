package robogp.matchmanager;

import java.util.ArrayList;
import java.util.Observable;
import robogp.deck.Deck;
import robogp.deck.InstructionCard;

/**
 *
 * @author Alessio
 */
public class mano {
    ArrayList<InstructionCard> mano = new ArrayList<>();
    private final int NUM_MANO = 8;
    
    public mano(){
        popolaMano();
    }
    
    public synchronized ArrayList<InstructionCard> getCards(){
        return this.mano;
    }

    public void setMano(ArrayList<InstructionCard> mano) {
        this.mano = mano;
    }
    
    
    
    public synchronized void popolaMano(){
        while(this.mano.size() < NUM_MANO){
            this.mano.add(Deck.getInstance().pickCard());
            System.out.println("Aggiunto scheda a mano");
        }
    }
    
    public synchronized void aggiungiScheda(InstructionCard carta){
        this.mano.add(carta);
    }
}


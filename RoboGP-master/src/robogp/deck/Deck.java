package robogp.deck;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class Deck{
    public static final int N_CARTE_MAX = 84;
    private static Deck singleInstance; //pattern singleton
    private ArrayList<InstructionCard> mazzo = new ArrayList(N_CARTE_MAX);//array carte nuove
    private ArrayList<InstructionCard> scarti = new ArrayList(N_CARTE_MAX);//array carte usate
    public static final String[] tipi = {"uturn", "backup", "move3", "move2", "move1", "turnleft", "turnright"};
    

    private Deck(){
        /*
        6   x  uturn
        6   x  backup
        6   x  move3
        12  x  move2
        18  x  move1
        18  x  turnleft
        18  x  turnright
        */
        
        for(int i = 0; i < 6; i++){
            InstructionCard card = new InstructionCard("uturn"); 
            mazzo.add(card);
            card = new InstructionCard("backup");
            mazzo.add(card);
            card = new InstructionCard("move3");
            mazzo.add(card);
        }
        for(int i = 0; i < 12; i++){
            InstructionCard card = new InstructionCard("move2");
            mazzo.add(card);
            
        }
        for(int i = 0; i < 18; i++){
            InstructionCard card = new InstructionCard("move1");
            mazzo.add(card);
            card = new InstructionCard("turnleft");
            mazzo.add(card);
            card = new InstructionCard("turnright");
            mazzo.add(card);
        }
        this.shuffle();//mischia le carte
    }


    public static Deck getInstance(){
        if(Deck.singleInstance == null)
            Deck.singleInstance = new Deck();
        return Deck.singleInstance;
    }
    
    
    public ArrayList<InstructionCard> getMazzo() {
        return mazzo;
    }

    public ArrayList<InstructionCard> getScarti() {
        return scarti;
    }

    //mischia il mazzo
    public void shuffle(){
        java.util.Collections.shuffle(this.mazzo);
    }


    //pesca la prima carta dal mazzo
    public InstructionCard pickCard(){
        InstructionCard card = mazzo.get(0); // prende la prima carta dal mazzo
        this.mazzo.remove(card); // la rimuove dal mazzo
        return card;
    }
    
    
    //aggiunge la carta da scartare in cima al mazzo degli scarti
    public void discard(InstructionCard _card){
        this.scarti.add(0, _card);
    }
    
    /**
     * preleva la carta specificata dal mazzo
     * @param _tipo il tipo di carta
     * @param _priorita la priorita della carta
     * @return InstructionCard se la trova, altrimenti null
    */
    public InstructionCard pickSpecificCard(String _tipo, int _priorita){
        InstructionCard card = null;
        boolean trovato = false;
        for(int i = 0; i < mazzo.size() && !trovato; i++){
            InstructionCard tempCard = mazzo.get(i);
            if(tempCard.getTipo().contentEquals(_tipo) && tempCard.getPriorita() == _priorita){ //true se viene trovata la carta corrispondente
                trovato = true;
                card = tempCard;
                mazzo.remove(card); //tolgo la carta dal pazzo
            }
        }
        //se non trova la carta allora non toglie niente da 'mazzo' e ritorna null
        return card;
    }
    
    
    @Override
    public String toString(){
        String s = "mazzo: ";
        for(int i = 0; i < mazzo.size(); i++){
            s += mazzo.get(i).toString();
        }
        s += " scarti: ";
        for(int i = 0; i < scarti.size(); i++){
            s += scarti.get(i).toString();
        }
        return s;
    }
}
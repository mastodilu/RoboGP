package robogp.deck;

import java.util.ArrayList;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class Deck{
    public static final int N_CARTE_MAX = 84;
    private static Deck singleInstance; //pattern singleton
    ArrayList<InstructionCard> mazzo = new ArrayList(N_CARTE_MAX);//array carte nuove
    ArrayList<InstructionCard> scarti = new ArrayList(N_CARTE_MAX);//array carte usate

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
            card = new InstructionCard("move1");
            mazzo.add(card);
        }
        for(int i = 0; i < 18; i++){
            InstructionCard card = new InstructionCard("turnleft");
            mazzo.add(card);
            card = new InstructionCard("turnright");
            mazzo.add(card);
        }

    }


    public static Deck getInstance(){
        if(Deck.singleInstance == null)
            Deck.singleInstance = new Deck();
        return Deck.singleInstance;
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
    
    
    //##############################################################################
    //classe che rappresenta la singola carta del mazzo
    private class InstructionCard{
        
        private String tipo;//uturn backup move3 move2 move1 turnleft turnright
        private int priorita_min;
        private int priorita_max;
        private String path_icona;

        private InstructionCard(String _tipo){
            switch (_tipo){
                case "uturn":{
                    createUTurn();
                    break;
                }
                case "turnleft":{
                    createTurnLeft();
                    break;
                }
                case "turnright":{
                    createTurnRight();
                    break;
                }
                case "backup":{
                    createBackUp();
                    break;
                }
                case "move1":{
                    createMove1();
                    break;
                }
                case "move2":{
                    createMove2();
                    break;
                }
                case "move3":{
                    createMove3();
                    break;
                }
            }
        }
        // -costruttore
    
        //crea la carta u-turn
        private void createUTurn(){
            this.tipo = "uturn";
            this.priorita_min = 10;
            this.priorita_max = 60;
            this.path_icona = "";
        }

        private void createTurnLeft(){
            this.tipo = "turnleft";
            this.priorita_min = 70;
            this.priorita_max = 410;
            this.path_icona = "";
        }

        private void createTurnRight(){
            this.tipo = "turnright";
            this.priorita_min = 80;
            this.priorita_max = 420;
            this.path_icona = "";
        }

        private void createBackUp(){
            this.tipo = "backup";
            this.priorita_min = 430;
            this.priorita_max = 480;
            this.path_icona = "";
        }

        private void createMove1(){
            this.tipo = "move1";
            this.priorita_min = 400;
            this.priorita_max = 660;
            this.path_icona = "";
        }

        private void createMove2(){
            this.tipo = "move2";
            this.priorita_min = 430;
            this.priorita_max = 480;
            this.path_icona = "";
        }

        private void createMove3(){
            this.tipo = "move3";
            this.priorita_min = 430;
            this.priorita_max = 480;
            this.path_icona = "";
        }
        
        @Override
        public String toString(){
            String s = "[";
            s += tipo + "]";
            return s;
        }

    }
}
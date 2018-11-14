package robogp.deck;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class InstructionCard{

    private String tipo;//uturn backup move3 move2 move1 turnleft turnright
    private int priorita_min; //usato per calcolare 'priorita'
    private int priorita_max; //usato per calcolare 'priorita'
    private int priorita; // priorita' della carta
    private String path_icona;

    public InstructionCard(String _tipo){
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
        this.path_icona = "./icons/card-uturn.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createTurnLeft(){
        this.tipo = "turnleft";
        this.priorita_min = 70;
        this.priorita_max = 410;
        this.path_icona = "./icons/card-turnL.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createTurnRight(){
        this.tipo = "turnright";
        this.priorita_min = 80;
        this.priorita_max = 420;
        this.path_icona = "./icons/card-turnR.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createBackUp(){
        this.tipo = "backup";
        this.priorita_min = 430;
        this.priorita_max = 480;
        this.path_icona = "./icons/card-backup.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove1(){
        this.tipo = "move1";
        this.priorita_min = 490;
        this.priorita_max = 660;
        this.path_icona = "./icons/card-move1.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove2(){
        this.tipo = "move2";
        this.priorita_min = 670;
        this.priorita_max = 780;
        this.path_icona = "./icons/card-move2.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    private void createMove3(){
        this.tipo = "move3";
        this.priorita_min = 790;
        this.priorita_max = 840;
        this.path_icona = "./icons/card-move3.png";
        this.priorita = randomWithRange(priorita_min, priorita_max);
    }

    @Override
    public String toString(){
        String s = "[";
        s += tipo + ":" + getPriorita() + "]";
        return s;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPath_icona() {
        return path_icona;
    }

    public int getPriorita() {
        return priorita;
    }
    
    
    private int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
    

}

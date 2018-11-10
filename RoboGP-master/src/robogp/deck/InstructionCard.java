package robogp.deck;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class InstructionCard{

    private String tipo;//uturn backup move3 move2 move1 turnleft turnright
    private int priorita_min;
    private int priorita_max;
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

package mockups;

import robogp.robodrome.Robodrome;

/**
 *
 * @author MatteoDiLucchio
 */
public class DontMessWithRobodromes {
    
    Robodrome robodrome;

    public DontMessWithRobodromes() {
        robodrome = new Robodrome("./robodromes/riskyexchange.txt");
    }
    
    
    public void main(String args[]){
        int r = robodrome.getRowCount();
        int c = robodrome.getColumnCount();
        System.out.println("r " + r + " c " + c);
    }
    
}

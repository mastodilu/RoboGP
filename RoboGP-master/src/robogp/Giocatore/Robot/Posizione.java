package robogp.Giocatore.Robot;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class Posizione{
    int riga;
    int colonna;

    Posizione(){
        this.riga = -1;
        this.colonna = -1;
    }

    Posizione(int r, int c){
        this.riga = r;
        this.colonna = c;
    }

    public int getRiga() {
        return riga;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }
    
    
}

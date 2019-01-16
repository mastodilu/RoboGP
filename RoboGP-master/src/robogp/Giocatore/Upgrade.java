package robogp.Giocatore;

public class Upgrade {
    public final String nome;
    public final int cariche;
    private int caricheRimanenti;
    private char sottofase;
    
    public Upgrade(String nome, int cariche, char sottofase) {
        this.nome = nome;
        this.cariche = cariche;
        this.caricheRimanenti = cariche;
        this.sottofase = sottofase;
    }
    
    public int getCaricheRimanenti(){
        return this.caricheRimanenti;
    }
    
    public void setCaricheRimanenti(int n){
        this.caricheRimanenti = n;
    }
    
    /**
     * Decrementa di uno le cariche rimanenti se queste erano maggiori di 0.
     */
    public void usa(){
        if(this.caricheRimanenti > 0){
            this.caricheRimanenti--;
        }
    }
    
    
    /**
     * @return true se c'e' almeno una carica rimanente.
     */
    public boolean usabile(){
        return this.caricheRimanenti > 0;
    }
    
}

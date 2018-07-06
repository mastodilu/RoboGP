package robogp.Giocatore;

public class Upgrade {
    public final String nome;
    public final String effetto;
    public final int cariche;
    private int caricheRimanenti;
    
    //ci serve un modo per avere un set iniziale di upgrade gia' inizializzati

    public Upgrade(String nome, String effetto, int cariche) {
        this.nome = nome;
        this.effetto = effetto;
        this.cariche = cariche;
        this.caricheRimanenti = cariche;
    }
    
    public int getCaricheRimanenti(){
        return this.caricheRimanenti;
    }
    
    public void setCaricheRimanenti(int n){
        this.caricheRimanenti = n;
    }
}

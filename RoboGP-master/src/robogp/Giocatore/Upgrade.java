
public class Upgrade {
    private String nome;
    private String effetto;
    private int caricheRimanenti;

    public Upgrade(String nome, String effetto, int caricheRimanenti) {
        this.nome = nome;
        this.effetto = effetto;
        this.caricheRimanenti = caricheRimanenti;
    }

    public String getNome() {
        return nome;
    }

    public String getEffetto() {
        return effetto;
    }

    public int getCaricheRimanenti() {
        return caricheRimanenti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEffetto(String effetto) {
        this.effetto = effetto;
    }

    public void setCaricheRimanenti(int caricheRimanenti) {
        this.caricheRimanenti = caricheRimanenti;
    }
    
    
}

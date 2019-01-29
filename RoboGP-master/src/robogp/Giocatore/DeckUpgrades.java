package robogp.Giocatore;

import java.util.ArrayList;

/**
 *
 * @author Matteo Di Lucchio <matteo.dilucchio@edu.unito.it>
 */
public class DeckUpgrades {
    
    private static DeckUpgrades singleInstance;
    
    /**
     * Upgrade disponibili per l'assegnamento
     */
    private ArrayList<Upgrade> upgradeList;
    
    /**
     * Upgrade usati
     */
    private ArrayList<Upgrade> scarti;
    
    /**
     * Indice della carta in cima al mazzo
     */
    private int indicePrimaCarta;
    
    
    
    
    private DeckUpgrades(){
        this.reset();
    }
    
    /**
     * Crea e aggiunge un RandomSwitch upgrade
     */
    private void randomSwitch(){
        this.upgradeList.add(
                new Upgrade("RandomSwitch",5,'A')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade copia&incolla
     */
    private void copiaIncolla(){
        this.upgradeList.add(
                new Upgrade("Copia&Incolla", 3, 'A')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade freni
     */
    private void freni(){
        this.upgradeList.add(
                new Upgrade("Freni", 5, 'B')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade acceleratore
     */
    private void acceleratore(){
        this.upgradeList.add(
                new Upgrade("Acceleratore", 3, 'B')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade retromarcia
     */
    private void retromarcia(){
        this.upgradeList.add(
                new Upgrade("Retromarcia", 5, 'B')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade shift
     */
    private void shift(){
        this.upgradeList.add(
                new Upgrade("Shift", 5, 'B')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade dual core
     */
    private void dualCore(){
        this.upgradeList.add(
                new Upgrade("DualCore", 3, 'B')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade disturbatore frequenze
     */
    private void disturbatoreFrequenze(){
        this.upgradeList.add(
                new Upgrade("DisturbatoreFrequenze", 1, 'C')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade disturbatore frequenze
     */
    private void giroscopio(){
        this.upgradeList.add(
                new Upgrade("Giroscopio", 5, 'C')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade scudo
     */
    private void scudo(){
        this.upgradeList.add(
                new Upgrade("Scudo", 3, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade super laser
     */
    private void superLaser(){
        this.upgradeList.add(
                new Upgrade("SuperLaser", 3, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade doppio laser
     */
    private void doppioLaser(){
        this.upgradeList.add(
                new Upgrade("DoppioLaser", 3, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade raggio respingente
     */
    private void raggioRespingente(){
        this.upgradeList.add(
                new Upgrade("RaggioRespingente", 5, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade raggio attrattore
     */
    private void raggioAttrattore(){
        this.upgradeList.add(
                new Upgrade("RaggioAttrattore", 5, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade raggio controllante
     */
    private void raggioControllante(){
        this.upgradeList.add(
                new Upgrade("RaggioControllante", 3, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade raggio disturbante
     */
    private void raggioDisturbante(){
        this.upgradeList.add(
                new Upgrade("RaggioDisturbante", 3, 'D')
        );
    }
    
    /**
     * Crea e aggiunge un upgrade raggio disturbante
     */
    private void retroLaser(){
        this.upgradeList.add(
                new Upgrade("RetroLaser", 3, 'D')
        );
    }
    
    
    /**
     * Mischia il mazzo.
     */
    private void shuffle(){
        indicePrimaCarta = 0;
        java.util.Collections.shuffle(this.upgradeList);
    }
    
    public void reset(){
        scarti = new ArrayList<Upgrade>();
        upgradeList = new ArrayList<Upgrade>();
        
        for(int i = 0; i < 2; i++){
            randomSwitch();
            freni();
            shift();
            disturbatoreFrequenze();
            giroscopio();
            scudo();
        }
        copiaIncolla();
        acceleratore();
        retromarcia();
        dualCore();
        superLaser();
        doppioLaser();
        raggioRespingente();
        raggioAttrattore();
        raggioControllante();
        raggioDisturbante();
        retroLaser();
        
        this.shuffle();
        System.out.println(this.toString());
    }
    
    /**
     * Pesca il primo upgrade.
     * @return l'upgrade pescato, altrimenti null.
     */
    public synchronized Upgrade pickOne(){
        //se l'ultima carta del mazzo e' gia' stata pescata
        if(indicePrimaCarta == upgradeList.size())
            reset();
        
        Upgrade up = upgradeList.get(indicePrimaCarta);
        indicePrimaCarta++;
        System.out.println(toString());
        return up;
        
    }
    
    /**
     * Aggiunge alla pila degli scarti l'upgrade passato.
     */
    public synchronized void scarta(Upgrade up){
        this.scarti.add(up);
    }
    
    
    public static DeckUpgrades getInstance(){
        if(singleInstance == null)
            DeckUpgrades.singleInstance = new DeckUpgrades();
        return singleInstance;
    }
    
    
    
    
    @Override
    public String toString() {
        
        String prima = "[" + this.indicePrimaCarta + "] ";
        String upgrade = "" + this.upgradeList.size() + " upgrades: ";
        String scarti = "" + this.scarti.size() + " upgrades scartati: ";
        
        for(Upgrade up : this.upgradeList)
            upgrade += up.toString() + "; ";
        for(Upgrade up : this.scarti)
            scarti += up.toString() + "; ";
        return prima + upgrade + scarti;
    }
}

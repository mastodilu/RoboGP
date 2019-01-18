//package robogp.matchmanager;
//
//import java.util.ArrayList;
//import robogp.Giocatore.Giocatore;
//import robogp.Giocatore.Robot.RobotMarkerPlaying;
//import robogp.robodrome.Robodrome;
//import robogp.robodrome.view.RobodromeView;
//
///**
// *
// * @author MatteoDiLucchio
// */
//public class StatoPartita {
//    
//    /**
//     * Giocatori in partita.
//     */
//    private ArrayList<Giocatore> giocatori;
//    
//    /**
//     * Numero massimo di giocatori.
//     */
//    private int numeroMaxGiocatori;
//    
//    /**
//     * Il numero di robot per giocatore.
//     */
//    private int numRobotPerGiocatore;
//    
//    /**
//     * True se i giocatori hanno un upgrade iniziale, false altrimenti.
//     */
//    private boolean upgradeIniziali;
//    
//    /**
//     * Robodromo della partita.
//     */
//    protected Robodrome robodrome;
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    // COSTRUTTORI
//    
//    public StatoPartita(
//            ArrayList<Giocatore> giocatori,
//            int robotPerGioc,
//            int numGioc,
//            Robodrome robodrome,
//            boolean upg){
//        this.giocatori = giocatori;
//        this.numRobotPerGiocatore = robotPerGioc;
//        this.numeroMaxGiocatori = numGioc;
//        this.robodrome = robodrome;
//        this.upgradeIniziali = upg;
//    }
//    
//    
//    public StatoPartita(
//            int robotPerGioc,
//            int numGioc,
//            Robodrome robodrome,
//            RobodromeView view,
//            boolean upg){
//        this.giocatori = new ArrayList<Giocatore>();
//        this.numRobotPerGiocatore = robotPerGioc;
//        this.numeroMaxGiocatori = numGioc;
//        this.robodrome = robodrome;
//        this.upgradeIniziali = upg;
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Aggiunge un giocatore all'elenco di giocatori e gli assegna il robot.
//     * Se nickname esiste già allora il robot viene assegnato al giocatore trovato,
//     * altrimenti viene creato un nuovo giocatore a cui assegnare il robot.
//     * @param nickname nome del giocatore
//     * @param robot robot da assegnare
//     */
//    protected void addPlayer(String nickname, RobotMarkerPlaying robot){
//        //se nickname non è nella lista di giocatori
//        //crea nuovo giocatore e assegna robot
//        //altrimenti assgna robot to giocatore
//        boolean assegnato = false;
//        for(Giocatore g : this.giocatori){
//            if(g.getNickname().equalsIgnoreCase(nickname)){
//                g.assegnaRobot(robot);
//                assegnato = true;
//                break;
//            }
//        }
//        if(!assegnato)
//            this.giocatori.add(new Giocatore(nickname, robot));
//    }
//}

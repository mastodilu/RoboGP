//package robogp.Giocatore.Robot;
//
//import robogp.matchmanager.RobotMarker;
//import robogp.robodrome.Direction;
//
//public class RobotMarkerPlaying extends RobotMarker{
////per sapere la posizione del robot (e anche quella iniziale) guardiamo 'dockNumber' della super classe invece di startX-Y
//private boolean spento;
//private int contatoreDanni;
//
//    public RobotMarkerPlaying(
//                    String name, String color, String owner ){
//        super(name, color); // usiamo il costruttore che ci hanno dato
//        this.assign(owner, startDock); //assegna owner e posizione iniziale
//        this.spento = false; //cominciano con un robot accesso, eventualmente si spegne durante il turno
//        this.contatoreDanni = 0;
//    }
//
//    public boolean isSpento() {
//        return spento;
//    }
//
//    public int getContatoreDanni() {
//        return contatoreDanni;
//    }
//
//    public void setSpento(boolean spento) {
//        this.spento = spento;
//    }
//
//    public void setContatoreDanni(int contatoreDanni) {
//        this.contatoreDanni = contatoreDanni;
//    }
//    
//}

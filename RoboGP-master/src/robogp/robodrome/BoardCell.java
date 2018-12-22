/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robogp.robodrome;

import java.awt.image.BufferedImage;
import robogp.robodrome.image.ImageUtil;
import robogp.robodrome.image.TileProvider;

/**
 * Rappresenta una generica cella del robodromo
 * @author claudia
 */
public class BoardCell {

    /**
     * Crea una casella del robodromo a partire da un array di stringhe che la descrivono
     * @param comps le stringhe che descrivono la casella
     * @param riga numero di riga della cella nel robodromo
     * @param colonna numero di colonna della cella nel robodromo
     * @return la casella creata
     */
    public static BoardCell createBoardCell(String[] comps, int riga, int colonna) {
        char t = comps[0].trim().charAt(0); // legge il carattere che rappresenta il tipo di cella
        String[] recomps = new String[comps.length - 1]; // crea un nuovo array di stringhe per memorizzare tutte le altre stringhe rimanenti del parametro passato a questo metodo
        for (int i = 0; i < recomps.length; i++) {
            recomps[i] = comps[i + 1].trim(); // memorizza le stringhe nel nuovo array creato
        }
        BoardCell b = null; // il costruttore e' 'protected'
        switch (t) { // a seconda del tipo
            case 'F':
                b = new FloorCell(recomps, riga, colonna);//cella normale
                break;
            case 'P':
                b = new PitCell(recomps, riga, colonna);//buco nero
                break;
            case 'B':
            case 'E':
                b = new BeltCell(recomps, t, riga, colonna); // nastro trasportatore (entrambi i tipi)
        }
        return b;
    }

    /**
     * F floor
     * P pit
     * B belt
     * E belt
     */
    private final char type; // F-P-B-E, il tipo di cella
    private boolean hLaser; //true se ha un laser orizzontale
    private boolean vLaser; // true se ha un laser verticale
    private int c, r; // numeri di riga e colonna della cella nel tabellone
    private boolean hasRobot = false; // true quando e' occupata da un robot
    
    @Override
    public String toString(){
        String result = "";
        result += (this.r+1) + "x" + (this.c+1) + " " + type
                + " BoardCell.java hLaser";
        result += this.hLaser? "+":"-";
        result += " vLaser" + (this.vLaser? "+":"-" + " ");
        
        return result;
    }

    public boolean hasHorizontalLaser() {
        return hLaser;
    }

    public boolean hasVerticalLaser() {
        return vLaser;
    }

    public void setHorizontalLaser(boolean v) {
        hLaser = v;
    }

    public void setVerticalLaser(boolean v) {
        vLaser = v;
    }

    BoardCell(char t, int r, int c) {
        this.type = t;
        this.r = r;
        this.c = c;
    }

    public char getType() {
        return type;
    }

    public BufferedImage getBaseImage() {
        return null;
    }

    public BufferedImage getTopImage() {
        return null;
    }

    public java.util.ArrayList<CellLaser> getLasers() {
        return new java.util.ArrayList<>();
    }

    public boolean hasWall(Direction d) {
        return false;
    }
    
    public int getRiga(){
        return this.r;
    }
    
    public int getColonna(){
        return this.c;
    }

    public BufferedImage getImage() {
        BufferedImage res = getBaseImage();
        BufferedImage limg = getLasersImage();
        if (limg != null) {
            res = ImageUtil.superImpose(res, limg);
        }
        BufferedImage topimg = getTopImage();
        if (topimg != null) {
            res = ImageUtil.superImpose(res, topimg);
        }
        return res;
    }

    BufferedImage getLasersImage() {
        BufferedImage result = null;
        if (hasHorizontalLaser()) {
            result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("Lh"));
        }
        if (hasVerticalLaser()) {
            result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("Lv"));
        }
        return result;
    }

    BufferedImage getWalls(boolean[] walls) {
        BufferedImage result = null;
        boolean[] wallset = java.util.Arrays.copyOf(walls, walls.length);
        if (wallset[0]) {
            wallset[0] = false;
            if (wallset[1]) {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("WW", Direction.W));
                wallset[1] = false;
            } else if (wallset[3]) {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("WW", Direction.S));
                wallset[3] = false;
            } else {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("W", Direction.W));
            }
        }

        if (wallset[1]) {
            wallset[1] = false;
            if (wallset[2]) {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("WW", Direction.N));
                wallset[2] = false;
            } else {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("W", Direction.N));
            }
        }
        if (wallset[2]) {
            wallset[2] = false;
            if (wallset[3]) {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("WW", Direction.E));
                wallset[3] = false;
            } else {
                result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("W", Direction.E));
            };
        }
        if (wallset[3]) {
            result = ImageUtil.superImpose(result, TileProvider.getTileProvider().getTile("W", Direction.S));
            wallset[3] = false;
        }
        return result;
    }
    
    
    /**
     * Vale true quando un robot la occupa, false altrimenti;
     */
    public boolean hasRobot(){
        return hasRobot;
    }
}

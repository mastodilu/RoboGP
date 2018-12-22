/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robogp.robodrome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Robodrome rappresenta un tabellone per il gioco RoboGP. 
 * @author claudia
 */
public class Robodrome {

    private final BoardCell[][] board;
    private final String name;
    private final int rows;
    private final int columns;
    private final java.util.ArrayList<CellLaser> allLasers;
    private int docksCount;
    
    @Override
    public String toString(){
        String result = "" ;
        result += this.name + " "
                + this.rows + "x" + this.columns
                + " nDocks " + this.docksCount + "\n";
        //laser
        for(int i = 0; i < this.allLasers.size(); i++){
            result += this.allLasers.get(i).toString();
        }
        result += "\n";
        //tabella
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                result += board[i][j].toString() + "\n";
            }
        }
        return result + "\n";
    }

    /**
     * Costruisce un robodromo a partire da un file che lo descrive.
     * @param filename il pathname del file
     */
    public Robodrome(String filename) {
        allLasers = new java.util.ArrayList<>();
        docksCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        //legge tutto il txt del robodromo e lo mette in una variabile
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(Robodrome.class.getName()).log(Level.SEVERE, null, ex);
        }//qui ha finito di leggere l'intero roboromo e lo puo' salvare in una variabile
        String[] all = stringBuilder.toString().split(";"); // crea il robodromo andando a leggere ogni singola cella (separate da ; )
        name = all[0].trim(); // nome del robodromo
        String[] dim = all[1].trim().split("x"); // array con le dimensioni del robodromo lette in seconda riga
        columns = Integer.parseInt(dim[0]);  // numero di colonne
        rows = Integer.parseInt(dim[1]); // numero di righe
        board = new BoardCell[rows][columns];
        int countcell = 0;
        boolean endfile = false;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!endfile) {
                    if (countcell + 2 < all.length) {
                        //crea la cella passando anche tutti i parametri che la riguardano: laser, muri, ecc
                        board[r][c] = BoardCell.createBoardCell(all[countcell + 2].trim().split("-"), r, c);
//                        stampa la cella come [riga colonna tipo]
                        //System.out.println(board[r][c].getRiga() + " " + board[r][c].getColonna() + " " + board[r][c].getType());
                        countcell++;
                        if (countcell + 2 >= all.length) {
                            endfile = true;
                        }
                        // disegna tutti i laser definita sulla cella
                        java.util.ArrayList<CellLaser> cellLasers = board[r][c].getLasers();
                        for (CellLaser las : cellLasers) {
                            las.setPosition(r, c);
                            allLasers.add(las);
                        }
                    }
                } else {
                    // ? aggiunge una cella alla fine di tutto ?
                    board[r][c] = new FloorCell(new String[0], r, c);
                }
                if (board[r][c] instanceof FloorCell && ((FloorCell)board[r][c]).isDock()) {
                    this.docksCount++; // conta le postazioni di partenza dei robot
                }
            }
        }
        for (CellLaser las : allLasers) {
            Direction dir = las.getWall();
            boolean stop = false;
            int r = las.getRow();
            int c = las.getCol();
            switch (dir) { //setta il laser in base alla direzione di fuoco
                case W:
                    while (!stop) {
                        board[r][c].setHorizontalLaser(true);
                        if (board[r][c].hasWall(Direction.E) || (c + 1) == columns || board[r][c + 1].hasWall(Direction.W)) {
                            stop = true;
                        } else {
                            c++;
                        }
                    }
                    break;
                case E:
                    while (!stop) {
                        board[r][c].setHorizontalLaser(true);
                        if (board[r][c].hasWall(Direction.W) || c == 0 || board[r][c - 1].hasWall(Direction.E)) {
                            stop = true;
                        } else {
                            c--;
                        }
                    }
                    break;
                case N:
                    while (!stop) {
                        board[r][c].setVerticalLaser(true);
                        if (board[r][c].hasWall(Direction.S) || (r + 1) == rows || board[r + 1][c].hasWall(Direction.N)) {
                            stop = true;
                        } else {
                            r++;
                        }
                    }
                    break;
                case S:
                    while (!stop) {
                        board[r][c].setVerticalLaser(true);
                        if (board[r][c].hasWall(Direction.N) || r == 0 || board[r - 1][c].hasWall(Direction.S)) {
                            stop = true;
                        } else {
                            r--;
                        }
                    }
                    break;
            }
        }
    }
    
    /**
     * Restituisce la casella nella posizione specificata
     * @param row la riga della casella
     * @param col la colonna della casella
     * @return la casella in posizione (riga, colonna)
     */
    public BoardCell getCell(int row, int col) {
        if (row < rows && col < columns) {
            return board[row][col];
        }
        return null;
    }

    /** 
     * @return il numero di righe nel tabellone
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * 
     * @return il numero di colonne nel tabellone
     */
    public int getColumnCount() {
        return columns;
    }

    /**
     *
     * @return il nome di questo robodromo
     */
    public String getName() {
        return name;
    }

    public int getDocksCount() {
        return this.docksCount;
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robogp.robodrome;

/**
 *
 * @author claudia
 */
public class CellLaser {
  private int row;
  private int col;
  private final Direction wall;
  
  @Override
  public String toString(){
      String result = "";
      result += (this.row+1)
              + "x" + (this.col+1)
              + " CellLaser.java wall " + this.wall;
      return result;
  }
  
  public CellLaser(Direction d) {
      wall = d;
  }
  
  public void setPosition(int r, int c) {
      row = r;
      col = c;
  }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the wall
     */
    public Direction getWall() {
        return wall;
    }
}

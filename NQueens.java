// Title: NQueens
// Author: Nathan Tamari
// handles graphics of nQueens problem
import javax.swing.*;
//import java.awt.Color.*;
import java.awt.*;

public class NQueens extends JPanel
{
  private int n, boxSize;
  private int[][] _solution;
  
  public NQueens(int setN) 
  { 
    n=setN;
    setLayout(null);
    setPreferredSize(new Dimension (600,600));
    
    _solution = NQueensV5.starter(n);
    boxSize = 600/(n);
    try {
      Thread.sleep(30);
      repaint();
    } catch (Exception e) {}
    finally {}     
  }
  
  public void paintComponent(Graphics g)
  {   
    ///////////////////////////////////////////
    // Constructs the board
    //////////////////////////////////////////
    g.clearRect(0,0,600,600);
    for(int i=0;i<n;i++)
    {
      g.drawLine(boxSize*i,0,boxSize*i,600);
      g.drawLine(0,boxSize*i,600,boxSize*i);
    }    
    g.setColor(Color.MAGENTA);
    for(int row = 0; row<n; row++)
    {
      for(int col = 0;col<n;col++)
      {
        if(_solution[row][col] == 1) g.fillRect(row*boxSize,col*boxSize,boxSize,boxSize);
      }
    }
  }
}
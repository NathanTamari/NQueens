// Title: NQueensDriver
// Author: Nathan Tamari
// Client class for NQueens

import javax.swing.JFrame;
//import javax.swing.*;
import javax.swing.JOptionPane;
public class NQueensDriver
{ 
  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Test");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    int maybe;
    
    do {
      //put everything in here
      String nString = JOptionPane.showInputDialog("N Value");
      int n = Integer.parseInt(nString);
      
      frame.getContentPane().add(new NQueens(n));
      
      frame.pack();
      frame.setVisible(true);
      maybe = JOptionPane.showConfirmDialog (null, "Would you like to continue the program?");
    } while(maybe == JOptionPane.YES_OPTION); 
    
    System.exit(0);
  }
} 
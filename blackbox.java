
package sada;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class blackbox {
    
    int x,y;
     Rectangle Block;
      public blackbox(int xPos, int yPos) {
          
          x=xPos;
          y=yPos;
      
          
      }
      public Rectangle getrect()
      {
          return Block;
      }
      public void draw(Graphics2D g2) {
         // Draws the Squares of the Stack 
          //places them in the line
   
         Block=new Rectangle(x,y-30,80,30);
               
                g2.setColor(Color.black);
                g2.fill(Block);
              
          
                
               
           
        
        
    }
}

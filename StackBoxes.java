
package sada;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;



public class StackBoxes {
      
      String Counters;
      int x,y;
      
      
      public StackBoxes(int xPos, int yPos,int counter) {
          
          x=xPos;
          y=yPos;
          Counters=Integer.toString(counter);
          
      }
      public Rectangle getRect() { // Get the rectangels 
		return new Rectangle(x, y,30 ,30 );
	}
    public void setX(int x) //Sets the  X
        {
            this.x=x;
        }
    public void setY(int y) //sets the y
    {
        this.y=y;
    }
    
    public void draw(Graphics2D g2) { //draws the stackboxes 
        
   
         Rectangle Block=new Rectangle(x,y,30,30); // draws the box of x and y positive and 30 and 30 width and height
                g2.setColor(Color.green);
                g2.draw(Block);
                g2.setColor(Color.black);
                g2.drawString(Counters, x+15, y+15);
          
                
               
           
        
        
    }
}

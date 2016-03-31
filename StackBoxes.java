


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;




public class StackBoxes {

	String counters;
	int x,y;

	/**
	 * creating a constructor for stack boxes
	 * @param xPos
	 * @param yPos
	 * @param counter
	 */
	public StackBoxes(int xPos, int yPos,int counter) {

		x=xPos;
		y=yPos;
		counters=Integer.toString(counter);

	}
	public Rectangle getRect() { // Get the rectangles 
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

	public void draw(Graphics2D g2) { //draws the stack boxes 


		Rectangle block=new Rectangle(x,y,30,30); // draws the box of x and y positive and 30 and 30 width and height
		g2.setColor(Color.green);
		g2.draw(block);
		g2.setColor(Color.black);
		g2.drawString(counters, x+15, y+15);
	}
}

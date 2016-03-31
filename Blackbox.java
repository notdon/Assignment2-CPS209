
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Blackbox {

	int x,y;
	Rectangle block;
	
	/**
	 * create a constructor for the black box
	 * @param xPos
	 * @param yPos
	 */
	public Blackbox(int xPos, int yPos) 
	{
		x=xPos;
		y=yPos;
	}
	
	/*
	 * method to return rectangle
	 */
	public Rectangle getrect()
	{
		return block;
	}
	
	/**
	 * methods to draw the block and set the color black
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		// Draws the Squares of the Stack 
		//places them in the line

		block=new Rectangle(x,y-30,80,30);

		g2.setColor(Color.black);
		g2.fill(block);
	}
}

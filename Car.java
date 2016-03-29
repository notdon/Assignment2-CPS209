
package sada;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


/**
 * The Class Car.
 */
public class Car extends Vehicle {


	/**
	 * Instantiates a new car.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
    int number;
     String numbers;
     StackBoxes box;
	public Car(int xPos, int yPos,int number) {
		super(xPos, yPos);
                this.number=number;
                numbers=Integer.toString(number);
	}


	/**
	 * Gets the rectangle
	 *
	 * @return the rect
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, VEHICLE_WIDTH, VEHICLE_HEIGHT);
	}
        public void setBox(StackBoxes box)
        {
            this.box=box;
        }
        public StackBoxes getBox()
        {
            return box;
        }

	/**
	 * Checks for has next
	 *
	 * @return true, if has next
	 */
	public boolean hasN() {
		return (next != null);
	}

	/**
	 * Checks if is truck.
	 *
	 * @return true, if is truck
	 */
	public boolean isTruck() {
		return false;
	}

	/**
	 * Gets the next
	 *
	 * @return the next
	 */
	public Vehicle getN() {
		if(!hasN()) return null;
		else return next;
	}

	/**
	 * Sets the next
	 *
	 * @param next the new next
	 */
	public void setN(Vehicle next) {
		this.next = next;
	}


	/**
	 * Checks for previous
	 *
	 * @return true, if has previous
	 */
	public boolean hasP() {
		return (previous != null);
	}

	/**
	 * Gets the previous
	 *
	 * @return the previous
	 */
	public Vehicle getP() {
		if(!hasP()) return null;
		else return previous;
	}

	/**
	 * Sets the previous
	 *
	 * @param previous the new previous
	 */
	public void setP(Vehicle previous) {
		this.previous = previous;
	}

	/**
	 * Move to.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x point
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y point
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Draws the method
	 *
	 * @param g2 the graphic
	 */
	public void draw(Graphics2D g2) {
		Rectangle body = new Rectangle(x, y + 10, 60, 10);      //Draws the Rectangle 
                g2.drawString(numbers, x+26, y+20);                     //writes the string
		Ellipse2D.Double frontTire = new Ellipse2D.Double(x + 10, y + 20, 10, 10); //draw the fronttire
		Ellipse2D.Double rearTire = new Ellipse2D.Double(x + 40, y + 20, 10, 10); //draws the reartire

		g2.draw(body);
		g2.draw(frontTire);
		g2.draw(rearTire);
		

		if(hasN()) //If the car has next draw that too
		{
			g2.drawLine(getRect().x+60, getRect().y+10, getN().getRect().x, getN().getRect().y+10);			
			getN().draw(g2);
		}
                
               
                
	}

}

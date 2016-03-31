
/**
 *
 * @author Al Khalil Alwaini
 * Student ID : 500666534
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Class Panel.
 */

public class Panel extends JPanel 
{

	/** The vehicle objects. */
	public LinkedList<Vehicle> vehicleObjects; //LinkedList object created with Type Vehicle as Generic type
	public LinkedList<StackBoxes>StackBoxs=new LinkedList<StackBoxes>();
	int moved;
	Blackbox blackbox;
	boolean moves=false;

	/** The selected vehicle. */
	public Vehicle selectedVehicle; //SelectedVehicle is saved ere

	/** The first time ran. */
	public boolean firstTime = true; 

	/** The first vehicle. */
	public Vehicle firstVehicle = null; 

	/** The delay. */
	public final int DELAY = 30;
	Vehicle c;
	int blackx,blacky;



	/**
	 * Instantiates a new panel.
	 */
	public Panel()
	{
		this.setBackground(Color.WHITE); //Background is white

		Handlerclass handler = new Handlerclass(); //Handler is created
		ActionListener timeListener = new TimeListener(); //Action listener is created

		this.addMouseListener(handler); // Mouse Action listener is created 
		this.addMouseMotionListener(handler); // Motion or drag listerner is created

		Timer timer = new Timer(DELAY, timeListener); //Timer is created 

		vehicleObjects = new LinkedList<Vehicle>(); //VehicleObject is initiated
		timer.start(); // Timer is started


	}


	/**
	 * Sets the up vehicle's
	 *
	 * @param event MouseEvent when pressed
	 */
	public void setUpVehicle(MouseEvent event) 
	{ // This just Adds Vehicle Class , Can be Car or the Truct

		if(vehicleObjects.size() == 0) 
		{ // when first pressed adds the trucl
			vehicleObjects.addLast(new Truck(event.getX(), event.getY()));
		} 

		else if(vehicleObjects.size() < 6)
		{ //when 2nd to 6th click , cars are added 

			vehicleObjects.addLast(new Car(event.getX(), event.getY(),vehicleObjects.size()));
		}
		else if(StackBoxs.size()==0) // after 6 clicks , the StackBox is created 
		{
			blackbox=new Blackbox(event.getX()-18, event.getY()+59);
			blackx=event.getX(); //Location of x axis and y axis
			blacky=event.getY();


			//Location of the stackboxes , how they stand in stack
			StackBoxs.addFirst(new StackBoxes(event.getX(), event.getY(),StackBoxs.size()+1));
			StackBoxs.addFirst(new StackBoxes(event.getX(), event.getY()-30,StackBoxs.size()+1));
			StackBoxs.addFirst(new StackBoxes(event.getX(), event.getY()-60,StackBoxs.size()+1));
			StackBoxs.addFirst(new StackBoxes(event.getX(), event.getY()-90,StackBoxs.size()+1));
			StackBoxs.addFirst(new StackBoxes(event.getX(), event.getY()-120,StackBoxs.size()+1));
		}

	}

	public void remove()
	{   
		if(selectedVehicle.hasN()) //check if the vehicle has more cars in queue 
		{ 
			while(selectedVehicle.hasN())
			{
				for (StackBoxes v : StackBoxs) { // checks whether the car have the stackboxes on them
					if(selectedVehicle.getRect().intersects(v.getRect()))
					{   
						selectedVehicle=selectedVehicle.getN(); //check if the cars are connected to it
					}
				}

				StackBoxs.get(moved).x=selectedVehicle.getX()+10; //Changes the stackboxes position along with the card
				StackBoxs.get(moved).y=selectedVehicle.getY()-20;

				moved++; // Moved keeps track of how many stack boxes have been moved
				break;
			}
		}

		else
		{ // if only one car is moved

			StackBoxs.get(moved).x=selectedVehicle.getX()+10;
			StackBoxs.get(moved).y=selectedVehicle.getY()-20;

			moved++;
		}
	}
	public void add()
	{

		if(moved==0) // if no stack boxes have been removed 
		{
			System.out.println("NO Stack Boxes moved");
		}

		else
		{ // if stackboxes have been moved
			for (StackBoxes v : StackBoxs) {
				if(selectedVehicle.getRect().intersects(v.getRect()))
				{   //checks if the selected vehicle has a stack on it , if so then the stack are moved back to the stack line
					v.x=blackx;
					v.y=blacky-((5-moved)*30);
					moved--;
					break;
				}
			}
		}
	}
	/**
	 * Check's if the vehicle have been added
	 *
	 * @return true, if successful 6 vehicle's have been added
	 */
	public boolean setUpVehicleDone() { 
		if (vehicleObjects.size() >= 6) {
			selectedVehicle = null; 
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the first car to the truck
	 */
	public void addFirst() {

		Vehicle v2;
		if(vehicleObjects.size() >= 6)
		{ //if all of the cars have been added 
			for(int i=0; i<vehicleObjects.size(); i++)
			{  
				if(vehicleObjects.get(i).equals(selectedVehicle)&&!vehicleObjects.get(i).hasP())
				{ //If only 1 car is present 
					if(!vehicleObjects.get(0).hasN())
					{ //if the have no next car
						vehicleObjects.get(0).setN(vehicleObjects.get(i)); // updates the previous and next
						vehicleObjects.get(i).setP(vehicleObjects.get(0));



						for (Vehicle v : vehicleObjects)
						{  //all this does is , It moves 80 away from the vehicles, As long as the vehicle has next it aparts 80 from previous
							if (v.hasN())
							{ // if vehicle has next
								for (StackBoxes s : StackBoxs)
								{  //check if each car has a box with it
									if(v.getN().getRect().intersects(s.getRect()))
									{
										s.x=v.getX()+85; // moves the boxes along with the car
										s.y=v.getY()-20;
									}
								}
								v.getN().moveTo(v.getX()+80, v.getY()); // move the car with it 
							}
						}
					}

					else
					{ // if the car has next car with i
						if(vehicleObjects.get(0).hasN()) 
						{              
							for (StackBoxes s : StackBoxs) 
							{
								if(vehicleObjects.get(0).getN().getRect().intersects(s.getRect()))
								{ // check if the car has the stack box with it                            
									s.x= vehicleObjects.get(0).getN().x+85;  //updates the stackbox location
									s.y= vehicleObjects.get(0).getN().y-20;                       
								}
							}
						}                    
						v2 = vehicleObjects.get(0).getN(); //updates the Next and previous nodes of each car
						v2.setP(null);
						vehicleObjects.get(0).setN(vehicleObjects.get(i));
						vehicleObjects.get(i).setP(vehicleObjects.get(0));//updates the Next and previous nodes of each car
						vehicleObjects.get(0).setN(vehicleObjects.get(i));//updates the Next and previous nodes of each car
						Vehicle getVec = vehicleObjects.get(i);//updates the Next and previous nodes of each car
						while(getVec.hasN()){
							getVec = getVec.getN();
						}
						getVec.setN(v2);//updates the Next and previous nodes of each car
						v2.setP(getVec);//updates the Next and previous nodes of each car

						for (Vehicle vp : vehicleObjects) 
						{  //all this does is , It moves 80 away from the vehicles, As long as the vehicle has next it aparts 80 from previous
							if (vp.hasN())
							{
								for (StackBoxes s : StackBoxs)
								{
									if(vp.getN().getRect().intersects(s.getRect()))
									{
										s.x=vp.getX()+85;
										s.y=vp.getY()-20;
									}
								}
								vp.getN().moveTo(vp.getX()+80, vp.getY());
							}
						}
					}
					selectedVehicle = null;
				}
			}
		}                                    
	}

	/**
	 * Adds the last car to the truck
	 */
	public void addLast()
	{  //adds the car to the last of truck
		if(vehicleObjects.size()>=6)
		{ // if all vehicles are added
			for(int i=0; i<vehicleObjects.size(); i++)
			{  
				if(vehicleObjects.get(i).equals(selectedVehicle)&&!vehicleObjects.get(i).hasP())
				{ //checks which car is selected and whether it has next or not 
					Vehicle v = vehicleObjects.get(0); // truck is selected 
					while(v.hasN())
					{ //traverses till there is not next
						v = v.getN();
					}
					v.setN(vehicleObjects.get(i)); //updates the last car's next
					vehicleObjects.get(i).setP(v);
				}
			}

			for (Vehicle v : vehicleObjects)
			{  //all this does is , It moves 80 away from the vehicles, As long as the vehicle has next it aparts 80 from previous
				if (v.hasN())
				{ // if vehicle has next
					for (StackBoxes s : StackBoxs) 
					{ 
						if(v.getN().getRect().intersects(s.getRect())) // if car has the box 
						{
							s.x=v.getX()+85; //update the box
							s.y=v.getY()-20;
						}
					}
					v.getN().moveTo(v.getX()+80, v.getY()); //update the car
				}
			}
		}
	}

	/**
	 * Removes the first car in the truck
	 */
	public void removeFirst() 
	{
		if(vehicleObjects.size()>=6)
		{
			Vehicle truck = vehicleObjects.get(0); // get the truck
			Vehicle chainAfterTruck = truck.getN();//next to the truck
			if (chainAfterTruck != null) 
			{
				if (chainAfterTruck.getN() != null) 
				{
					Vehicle chainAfterTruck2 = chainAfterTruck.getN(); //2nd car from the truck
					chainAfterTruck.setN(null); //updates the next and previous 
					chainAfterTruck.setP(null);;
					truck.setN(chainAfterTruck2);
				} 
				else 
				{
					chainAfterTruck.setN(null);
					chainAfterTruck.setP(null);
					truck.setN(null);
				}
			}
		}		
	}

	/**
	 * Removes the last car in the truck (Does not work)
	 */
	public void removeLast()
	{
		if(vehicleObjects.size()>=6)
		{            
			Vehicle truck = vehicleObjects.get(0); // truck
			Vehicle Prev= null; 
			while(truck.getN()!=null) // till last car
			{
				Prev=truck;
				truck=truck.getN();
			}
			Prev.setN(null); // update the last car
			truck.setP(null);
		}
	}

	/**
	 * The Class Handlerclass that takes care of mouse events
	 */
	public class Handlerclass implements MouseListener, MouseMotionListener {


		@Override
		public void mouseDragged(MouseEvent e) 
		{ // if mouse dragged this is executed 
			moves=false;
			if (selectedVehicle != null && !selectedVehicle.isTruck()) //if atleast a car is selected and its not a car
			{ 

				//while(!selectedVehicle.hasN())
				for (StackBoxes s : StackBoxs)
				{ // checks all the stackboxes
					if(selectedVehicle.getRect().intersects(s.getRect())) // check if the card has a box
					{                                                
						moves=true; //updates stack
						s.x=e.getX()+15;
						s.y=e.getY()-20;
						break;
					}                                            
				}
				selectedVehicle.moveTo(e.getX(), e.getY()); //updates the selectedV
				//all this does is , It moves 80 away from the vehicles, As long as the vehicle has next it aparts 80 from previous
				for (Vehicle v : vehicleObjects)
				{
					// if v has a next car
					if (v.hasN())
					{
						for (StackBoxes s : StackBoxs)
						{ // check all the boxes on all the cars 
							if(v.getN().getRect().intersects(s.getRect()))
							{
								s.x=v.getX()+85; // updates the boxes 
								s.y=v.getY()-20;
							}
						}
						v.getN().moveTo(v.getX()+80, v.getY()); //updates the vehicles 
					}
				}
			}

		}

		/**
		 * Mouse Pressed. Selects or Add's Vehicle
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{            
			setUpVehicle(e); //Set the vehicle in array
			if (setUpVehicleDone())
			{ //Checks if vehicles are more than or less than 6
				for (Vehicle v : vehicleObjects)
				{ 
					if (v.hasP() == false && v.getRect().contains(e.getPoint()))
					{ //hasP means has previous
						selectedVehicle = v;
						break;
					} 

					else 
					{
						selectedVehicle = null;
					}

				}

			}
		}

		/**
		 * Mouse Released. Connects with another car if close enough
		 */
		@Override
		public void mouseReleased(MouseEvent e) {

			if (selectedVehicle != null)
			{
				for(Vehicle vehicle : vehicleObjects)
				{  // Check all the cars 
					if(vehicle.equals(selectedVehicle) == false)
					{
						if(vehicle.getRect().intersects(selectedVehicle.getRect()))
						{ //Checks the proximity of the cars
							if(vehicle.getN() == null)
							{ // if vehicles has next 
								vehicle.setN(selectedVehicle);  //it also selected the next cars 
								if (firstTime)
								{ 
									firstVehicle = vehicle;
									firstTime = false;	
								}
							}
						}
					}
				}
				for (Vehicle v : vehicleObjects)
				{  //all this does is , It moves 80px away from the vehicles, As long as the vehicle has next it aparts 80 from previous
					if (v.hasN())
					{ // if car has next 
						for (StackBoxes s : StackBoxs)
						{ // if it has the stack
							if(v.getN().getRect().intersects(s.getRect()))
							{  
								s.x=v.getX()+85;
								s.y=v.getY()-20;//updates the stack boxes 
							}
						}                   
						v.getN().moveTo(v.getX()+80, v.getY()); //updates the car 
					}
				}
			}
		}

		//Do-Nothing Methods
		@Override
		public void mouseMoved(MouseEvent e) 
		{

		}
		@Override
		public void mouseClicked(MouseEvent e) 
		{

		}
		@Override
		public void mouseEntered(MouseEvent e) 
		{

		}
		@Override
		public void mouseExited(MouseEvent e)
		{

		}
	}

	/**
	 * Reset vehicles and removes cars/trucks to to New.
	 */
	public void resetVehicles()
	{
		for (Vehicle v: vehicleObjects) 
		{
			v.setN(null);
			v.setP(null);
		}
		vehicleObjects.clear();
	}


	/**
	 * Paints the panel to display various objects
	 */
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for (Vehicle v : vehicleObjects)
		{ //checks all the cars 
			if(v.hasP() == false) {
				if(v.equals(selectedVehicle)) {  // if car has no previous 
					g2.setColor(Color.red);
				} else {
					g2.setColor(Color.black);
				}

				v.draw(g2);
			}
		}
		if (vehicleObjects.size() >= 6)
		{
			for (Vehicle v : vehicleObjects)
			{
				if (v.equals(selectedVehicle) && selectedVehicle.hasN() && selectedVehicle.getP() !=  vehicleObjects.get(0))
				{//if the cars has next , and is selected , then update all the cars with red color 
					g2.setColor(Color.red);
					v.draw(g2);
				} 
			}
			if(StackBoxs.size()!=0)
			{
				blackbox.draw(g2); // if car has more than 1 stack. for example, 5 stack, then draw them
				for (StackBoxes v : StackBoxs)
				{            
					v.draw(g2);
				}
			}
		}
	}

	/**
	 * The listener interface for receiving time events.
	 */
	class TimeListener implements ActionListener {

		/**
		 * Action Performed after every time that passes by
		 */
		public void actionPerformed(ActionEvent event) {
			repaint();
		}
	}
}

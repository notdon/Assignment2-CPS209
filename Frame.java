

package sada;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



@SuppressWarnings("serial")
public class Frame extends JFrame {

	/** The selected vehicle. */
	public Vehicle selectedVehicle;
	
	/** The panel. */
	public Panel panel;
	
	/**
	 * Instantiates a new frame.
	 */
	public Frame() {
		
		setTitle("Assignment 2 CPS209"); //Sets the tile
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when press cross button
		setSize(650, 650); //size of the screen
		setResizable(false);   // no resizable
		setLocationRelativeTo(null); 
		panel = new Panel();         
		add(panel); // add panel to JFRAME
		
		//MENU
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar(menuBar);
		
		//FILE
		JMenu file = new JMenu("File"); //JMenu part
		menuBar.add(file);
		
		JMenuItem restart = new JMenuItem("New"); //JMenu Items
		file.add(restart); // restarts the program
		restart.setActionCommand("New"); //Adds the action listener
		restart.addActionListener(new FileListener());
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		exit.setActionCommand("Exit");
		exit.addActionListener(new FileListener());
		//FILE DONE
		
		//EDIT
		JMenu Queue = new JMenu("Queue"); //Another Jmenu part
		menuBar.add(Queue); //adds the Queue in the MenuBar
		
		JMenuItem Remove = new JMenuItem("Remove"); //Another item
		Queue.add(Remove); // Added to Jmenu
                Remove.setActionCommand("Remove");
		Remove.addActionListener(new EditListener());
		
		JMenuItem add = new JMenuItem("Add");
		Queue.add(add);
		add.setActionCommand("Add");
		add.addActionListener(new EditListener());
		// EDIT DONE
		
		
		//LIST
		JMenu list = new JMenu("List"); //Another Jmenu part
		menuBar.add(list);

		JMenuItem addFirst = new JMenuItem("Add First");
		list.add(addFirst);
		addFirst.setActionCommand("Add First");
		addFirst.addActionListener(new ListListener());
		
		JMenuItem addLast = new JMenuItem("Add Last");
		list.add(addLast);
		addLast.setActionCommand("Add Last");
		addLast.addActionListener(new ListListener());
		
		JMenuItem removeFirst = new JMenuItem("Remove First");
		list.add(removeFirst);
		removeFirst.setActionCommand("Remove First");
		removeFirst.addActionListener(new ListListener());
		
		JMenuItem removeLast = new JMenuItem("Remove Last (Does Not Work)");
		list.add(removeLast);
		removeLast.setActionCommand("Remove Last");
		removeLast.addActionListener(new ListListener());
		//LIST DONE
		
		setVisible(true);

	}
	
	/**
	 * The listener interface for receiving list events.
	 */
	class ListListener implements ActionListener {
		
		/**
		 * Action Performed when a certain menu item is clicked on
		 */
		public void actionPerformed(ActionEvent e) {
			String eventName = e.getActionCommand();
			
			if (eventName.equals("Add First")) { // If Add first was pressed 
				panel.addFirst(); //Executre the addfirst() 
			} else if (eventName.equals("Add Last")) { //if the add last is pressed
				panel.addLast(); //executre the addlast part
			} else if (eventName.equals("Remove First")) {
				panel.removeFirst();
			} else if (eventName.equals("Remove Last")) {
				panel.removeLast();
			}
                        
		}
	}
	
	
	/**
	 * The listener interface for receiving file events.
	 */
	class FileListener implements ActionListener {
		
		/**
		 * Action Performed when a certain menu item is clicked on
		 */
		public void actionPerformed(ActionEvent e) {
			String eventName = e.getActionCommand();
			
			if (eventName.equals("New")) { // If new is pressed 
				panel.resetVehicles(); //execute the resetVehicle()
			} else if (eventName.equals("Exit")) {
				System.exit(0);
			}
			
		}
	}
	
	/**
	 * The listener interface for receiving edit events.
	 */
	class EditListener implements ActionListener {
		
		/**
		 * Action Performed when a certain menu item is clicked on
		 */
		public void actionPerformed(ActionEvent e) {
			String eventName = e.getActionCommand();
			
			if (eventName.equals("Remove")) {
				panel.remove();
			} else if (eventName.equals("Add")) {
				panel.add();
                        }
			
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Frame frame = new Frame(); //Self initizalizer
	}
}


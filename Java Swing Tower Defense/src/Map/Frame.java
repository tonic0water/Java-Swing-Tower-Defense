package Map;
import javax.swing.JFrame;

public class Frame extends JFrame{			//using JFrame as the basis for the scree/frame

	public static java.util.Scanner sc = new java.util.Scanner(System.in); // scanner is opened to be used for getting user inputs
	
	public static void main (String[]args)	
	{
		
		int width = 5;				//this will be the number of columns
		int height = 5;

		
		System.out.println("How many columns? ");
		String stringHeight = sc.nextLine();
		if (stringHeight == null)
		{
			System.out.println("Cannot put blank number of columns. set to default of 5");
			height = 5;			
		}
		else
		{
		height = Integer.parseInt(stringHeight);		// string is parsed for an integer - if no double exists will through an exception - this is okay because data is always saved
		}

		
		System.out.println("How many rows? ");
		String stringWidth = sc.nextLine();
		if (stringWidth == null)
		{
			System.out.println("Cannot put blank number of rows. set to default of 5");
			width = 15;
		}
		else
		{
			width = Integer.parseInt(stringWidth);		// string is parsed for a double - if no double exists will through an exception - this is okay because data is always saved
		}
		
		new Frame(height, width);         		//creates new frame class
		
	}
	
	public Frame(int w, int h)        			// constructor for frame
	{
		new JFrame();							// creates instance of jFrame
		
		this.pack();

		this.setSize(900, 1050);								// gives dimensions of the jframe
		this.setTitle("Tower Defense Map");					// gives title to the frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		// makes window close when x is hit
		//this.setExtendedState(MAXIMIZED_BOTH);			// takes up entire screen to start - this can be activated along with the line below to make it fit the entire screen
		//this.setUndecorated(true);
		this.setResizable(false); 							// can be resized
		this.setVisible(true);						
		this.setLocationRelativeTo(null); 					// makes the screen pop up in the middle of the screen
		
		
		Screen screen = new Screen(this,w,h);				// passes the frame just created to the screen class - also the height and width specified by the user 
		this.add(screen);								// adds screen to the jframe - allows screen class to paint onto screen
	}
}

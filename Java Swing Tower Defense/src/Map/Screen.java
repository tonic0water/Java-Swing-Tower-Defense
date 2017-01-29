package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

import critterMain.Critter;
import critterMain.TrooperCritter;


public class Screen extends JPanel implements Runnable{						//this extends the JPanel, and implements Runnable - meaning it calls the run method
	// this class will be the central class for the game
	Thread thread = new Thread(this);

	Frame frame;
	public int scene;					// the scene defines what screen you are on (EX. main menu, map creator...)

	public int hand = 0;				// this hand is used for the mouse clicking detection
	public int XPosOfCursor = 0;		// this variable represents the X position of the cursor on the screen	
	public int YPosOfCursor = 0;		// this variable represents the Y position of the cursor on the screen

	private int Money=500;				// this is the money variable, which keeps track of the players available funds
	private int Lives = 10;				// this is the lives variable which keeps track of the players remaining lives 
	private int XgridNumber;			// this variable represents the number of columns the user decided to have on the map
	private int YgridNumber;			// this variable represents the number of rows the user decided to have on the map
	private int horizontalSizeOfSquares; // this variable defines the width of one square of the map
	private int verticalSizeOfSquares;	// this variable defines the height of one square on the map
	private ArrayList<Integer> roadSquares = new ArrayList<>();	// this arrayList keeps track of where the user wanted to have the road
	private int frameWidth;				// this variable saves the users computer screen width
	private int frameHeight;			// this variable saves the users computer screen height

	private int numberOfTowers = 6; 	// this integer can be changed to accommodate more tower spots on the map
	private String validity = "";		// this string will be changed and used to print whether or not a map is valid
	private int saveMap = 0;
	private int convertMap = 0;
	private int isValid = 0;
	private ArrayList<Integer> convertedRoute = new ArrayList<Integer>();
	private ArrayList<String> mapNames = new ArrayList<String>();
	private ArrayList<ArrayList<Integer>> storedMaps = new ArrayList<ArrayList<Integer>>();
	private MapSquare[][] mapSquareLayout;
	private RoadMapList roadList = new RoadMapList();
	private boolean traversed = false;

	public Screen(Frame frame, int width, int height)
	{
		XgridNumber = width;		// sets the number of columns to that specified by the user
		YgridNumber = height;		// sets the number of rows to that specified by the user

		this.frame = frame;

		thread.start();					// runs the run method

	}

	public void paintComponent (Graphics gr)		// this is what updates the screen when it repaints
	{
		gr.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());     // clears the last layer - stops lag
		frameWidth = this.frame.getWidth();
		frameHeight = this.frame.getHeight();

		if(scene ==0)					// scene 0 is the welcome menu - not much functionality other than welcoming the user
		{
			gr.setColor(Color.BLUE);
			gr.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
			gr.setColor(Color.RED);
			gr.setFont(gr.getFont().deriveFont(18, 50));
			gr.drawString("Welcome to tower Defense", this.frame.getWidth()/8, this.frame.getHeight()/3);
			gr.setFont(gr.getFont().deriveFont(18, 25));
			gr.drawString("Press Space bar to continue to Game", this.frame.getWidth()/8, this.frame.getHeight()/2);
			gr.setFont(gr.getFont().deriveFont(18, 15));

		}
		else if (scene ==1)				// scene 1 is the main game menu - from here the user can start the game or create a map
		{

			gr.setColor(Color.LIGHT_GRAY);
			gr.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

			horizontalSizeOfSquares = this.frame.getWidth()/(4 + XgridNumber);
			verticalSizeOfSquares = this.frame.getHeight()/(4 + YgridNumber);

			for (int x = 0; x<XgridNumber;x++)				// double for loop creates the map
				for (int y=0; y<YgridNumber; y++)
				{
					gr.setColor(Color.GREEN);
					gr.fillRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
							horizontalSizeOfSquares, verticalSizeOfSquares);
					gr.setColor(Color.BLACK);
					gr.drawRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
							horizontalSizeOfSquares, verticalSizeOfSquares);
					if(y==YgridNumber/2)					// this if statement causes the initial map with the road through the middle to be created
					{
						gr.setColor(Color.DARK_GRAY);
						gr.fillRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
								horizontalSizeOfSquares, verticalSizeOfSquares);
						gr.setColor(Color.BLACK);
						gr.drawRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
								horizontalSizeOfSquares, verticalSizeOfSquares);
						if(!traversed){
							if(roadList.getStart() != null){

								roadList.addEnd(horizontalSizeOfSquares +(x*horizontalSizeOfSquares)+1,verticalSizeOfSquares + (y*verticalSizeOfSquares)+1);
							}
							else{
								roadList.addStart(horizontalSizeOfSquares +(x*horizontalSizeOfSquares)+1, verticalSizeOfSquares + (y*verticalSizeOfSquares)+1);
							}
						}
					}
				}
			traversed = true;
			// these are the texts on the screen
			gr.drawRect(horizontalSizeOfSquares, (int)((YgridNumber+1.25)*verticalSizeOfSquares),  (int)(1.5*horizontalSizeOfSquares),verticalSizeOfSquares);
			gr.drawString("Money:  " + Money, horizontalSizeOfSquares+2, (int)((YgridNumber+1.25)*verticalSizeOfSquares)+13);
			gr.drawRect(horizontalSizeOfSquares, (int)((YgridNumber+2.5)*verticalSizeOfSquares),  (int)(1.5*horizontalSizeOfSquares),verticalSizeOfSquares);
			gr.drawString("Lives: " + Lives, horizontalSizeOfSquares+2, (int)((YgridNumber+2.5)*verticalSizeOfSquares)+13);
			gr.setFont(gr.getFont().deriveFont(18, 20));
			gr.drawString("Press m to create", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares) ;
			gr.drawString("custom map", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares)+25,verticalSizeOfSquares+35);
			gr.drawString("Press s to start game", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+100) ;
			gr.setFont(gr.getFont().deriveFont(18, 15));

			Critter crit = new TrooperCritter(1);
			crit.moveTo(roadList.getStart().getXposition(), roadList.getStart().getYposition());
			gr.drawImage(crit.sprite, (int)crit.getPos().getxPos(),(int)crit.getPos().getyPos() , null);
			roadList.removeFirst();



			for (int x=0; x<((numberOfTowers/2)-1);x++)			// this draws the boxes for the towers
			{
				for (int y = 0; y<2; y++)
				{
					gr.drawRect((int)(horizontalSizeOfSquares*3.5) + (int)(0.75*x*horizontalSizeOfSquares) , (int)((YgridNumber+1.4)*verticalSizeOfSquares +y*verticalSizeOfSquares),  (int)(1.5*horizontalSizeOfSquares),verticalSizeOfSquares);
				}
			}
			gr.drawString("Towers:", (int)(horizontalSizeOfSquares*3.5), (int)((YgridNumber+1.4)*verticalSizeOfSquares)-1);

		}
		else if (scene ==2)				// scene 2 is the map creator
		{
			this.frame.addMouseListener (new MouseHandler(this));			// this checks for the mouse being used - the mouse used in any other scene is useless
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

			int horizontalSizeOfSquares = this.frame.getWidth()/(4 + XgridNumber);
			int verticalSizeOfSquares = this.frame.getHeight()/(4 + YgridNumber);

			for (int x = 0; x<XgridNumber;x++)			// this creates the graph
				for (int y=0; y<YgridNumber; y++)
				{
					gr.setColor(Color.GREEN);
					gr.fillRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
							horizontalSizeOfSquares, verticalSizeOfSquares);	
					for (int z = 0; z<roadSquares.size(); z+=2)				// this searches through the roadSquares array (which has the positions the user indicated for road) and paints those spots on the map
					{
						if (roadSquares.get(z)>horizontalSizeOfSquares +(x*horizontalSizeOfSquares)&&roadSquares.get(z+1)>(verticalSizeOfSquares + (y*verticalSizeOfSquares))&&
								roadSquares.get(z)<(2*horizontalSizeOfSquares +(x*horizontalSizeOfSquares)) && roadSquares.get(z+1)<(2*verticalSizeOfSquares + (y*verticalSizeOfSquares)))
						{
							if (z==0||z==2)			// this makes the entry and exit spots red for easier viewing
							{
								gr.setColor(Color.RED);
								gr.fillRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
										horizontalSizeOfSquares, verticalSizeOfSquares);
								if(roadList.getStart() != null){
									roadList.addEnd(horizontalSizeOfSquares +(x*horizontalSizeOfSquares)+1,verticalSizeOfSquares + (y*verticalSizeOfSquares)+1);
								}
								else{
									roadList.addStart(horizontalSizeOfSquares +(x*horizontalSizeOfSquares)+1,verticalSizeOfSquares + (y*verticalSizeOfSquares)+1);
								}
							}
							else
							{
								gr.setColor(Color.DARK_GRAY);
								gr.fillRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
										horizontalSizeOfSquares, verticalSizeOfSquares);
								roadList.addMid(horizontalSizeOfSquares +(x*horizontalSizeOfSquares)+1,verticalSizeOfSquares + (y*verticalSizeOfSquares)+1);
							}
						}
					}
					gr.setColor(Color.BLACK);
					gr.drawRect(horizontalSizeOfSquares +(x*horizontalSizeOfSquares), verticalSizeOfSquares + (y*verticalSizeOfSquares),
							horizontalSizeOfSquares, verticalSizeOfSquares);

				}
			// defines the instructions
			gr.setFont(gr.getFont().deriveFont(18, 15));
			gr.drawString("Instructions:", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares) ;
			gr.drawString("1) Select start point", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+35);
			gr.drawString(" - must be in column 1", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+70);
			gr.drawString("2) Select endpoint", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+105);
			gr.drawString(" - must be in last column", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+140);
			gr.drawString("3) Connect start to end point", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+175);
			gr.drawString(" - must connect smoothly", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+210);
			gr.drawString(" - must go start to end", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+245);
			gr.drawString(" Press F when finished", horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+350);
			gr.drawString(validity, horizontalSizeOfSquares +(int)((XgridNumber+0.5)*horizontalSizeOfSquares),verticalSizeOfSquares+500);


		}

		else 
		{
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
		}

		gr.setColor(Color.black);
	}

	public void run()
	{		
		scene = 0;
		importMaps();


		while(true)				// while loop that runs the game
		{
			this.frame.addKeyListener(new KeyHandler(this));			// this waits for user to input commands through the keyboard
			/*Timer timer = new Timer(200, new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();

				}

			});
			timer.start();			// repaints the screen continually
			 */
			repaint();
			try {
				Thread.sleep(200);					// this causes a pause so that it does not update the screen so quickly 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void importMaps()
	{
		File file = new File("Maps.csv");
		try {
			Scanner sc = new Scanner(file);						// initializes scanner on file
			sc.useDelimiter(",");								// specifies new space to be separated by ,
			while (sc.hasNext())								// loops through to get all entries
			{
				ArrayList<Integer> tempMap = new ArrayList<Integer>();
				String input = new String(sc.nextLine());			// pulls the string as one piece from the csv file
				String noSpaceInput = input.replaceAll(" +", "");	// removes all spaces 
				String[] singleEntries = noSpaceInput.split(",");	// creates a sub array split at commas
				String mapName = singleEntries[0];
				mapNames.add(mapName);
				for (int i = 1; i<singleEntries.length; i++)
				{
					tempMap.add(Integer.parseInt(singleEntries[i]));
				}
				System.out.println("Map should have been added");
				storedMaps.add(tempMap);

			}
			sc.close();												// scanner closed
		} catch (FileNotFoundException e) {							// scanner must be in try catch
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class MouseHeld					// this class checks to see if the mouse is held
	{
		boolean mouseDown = false;			// change when mouse is used

		public void mouseMoved(MouseEvent e) {
			XPosOfCursor = e.getXOnScreen();
			YPosOfCursor = e.getYOnScreen();


		}

		public void updateMouse(MouseEvent e)
		{
			if (scene ==2)				// this checks to see if the mouse is being used in scene 2 and on the the map 
			{
				if (mouseDown && hand ==0)
				{
					if(e.getXOnScreen() >=horizontalSizeOfSquares && e.getXOnScreen()<= horizontalSizeOfSquares +((XgridNumber)*horizontalSizeOfSquares))
					{
						if (e.getYOnScreen() >= verticalSizeOfSquares && e.getYOnScreen()<= verticalSizeOfSquares + (YgridNumber*verticalSizeOfSquares))
						{

							if (roadSquares.isEmpty()) // this means you are clicking the entry point
							{	// this if statement checks if the entry point is valid (in leftmost column)
								if(e.getXOnScreen()>=horizontalSizeOfSquares&&e.getXOnScreen()<= horizontalSizeOfSquares +horizontalSizeOfSquares&&
										(e.getYOnScreen() >= verticalSizeOfSquares && e.getYOnScreen()<= verticalSizeOfSquares + (YgridNumber*verticalSizeOfSquares)))
								{
									roadSquares.add(e.getXOnScreen());
									roadSquares.add(e.getYOnScreen());
								}

							}
							else if (roadSquares.size()==2)		// if you are element 2 of the list you are typing in the end point (0 is x of entry, 1 i y of entry)
							{		//this checks if the selected point is a valid exit point
								if(e.getXOnScreen()>=horizontalSizeOfSquares +((XgridNumber-1)*horizontalSizeOfSquares)&&e.getXOnScreen()<=horizontalSizeOfSquares +((XgridNumber)*horizontalSizeOfSquares)&&
										(e.getYOnScreen() >= verticalSizeOfSquares && e.getYOnScreen()<= verticalSizeOfSquares + (YgridNumber*verticalSizeOfSquares)))
								{
									roadSquares.add(e.getXOnScreen());
									roadSquares.add(e.getYOnScreen());
								}
							}
							else if (roadSquares.get(roadSquares.size()-1) != e.getYOnScreen())	//this checks that there are no duplicates being added to the list in a row
							{
								roadSquares.add(e.getXOnScreen());
								roadSquares.add(e.getYOnScreen());
							}
						}

					}
				}
			}
		}


		public void mouseDown(MouseEvent e) {			// this checks if the mouse is pressed
			mouseDown = true;

			if(hand!=0)
			{
				hand = 0;				//when called changes hand to 0 which ininitates the updateMouse 
			}

			updateMouse(e);			// the mouse event e is sent from the MouseHandler Method

		}

	}

	public class KeyTyped					// this class is called when the keys are typed by the KeyHandler class
	{
		public void keyESC()				// these methods tell the associated actions when a key is pressed 
		{
			System.exit(0);			// esc exits the game

		}

		public void keySPACE() {
			scene = 1;				// space changes to scene 1

		}
		public void keyM() {
			saveMap =0;
			convertMap = 0;	
			isValid = 0;// ************** wrong spot put in start game button		
			scene = 2;				// m goes to map builder


		}


		public void keyF() {
			convertRoute(roadSquares);
			isValid();	// f checks validity of map

		}

		private void convertRoute(ArrayList<Integer> roadRoute)
		{
			if (convertedRoute.isEmpty())
			{
				for (int i = 0; i<roadRoute.size(); i+=2)				// this method fills the new array list with the old array list values, but reduced to integers 1,2,3... 
				{
					convertedRoute.add((((XgridNumber+4)*roadRoute.get(i))/frameWidth));

					convertedRoute.add((((YgridNumber+4)*roadRoute.get(i+1))/frameHeight));
					System.out.println(roadRoute.size());
				}

			}
		}
		private void isValid() {		// this method checks to see if the map entered by the user is valid

			/*for (int i = 0; i<roadRoute.size(); i+=2)				// this method fills the new array list with the old array list values, but reduced to integers 1,2,3... 
			{

					convertedRoute.add(((XgridNumber+4)*roadRoute.get(i))/frameWidth);

					convertedRoute.add(((YgridNumber+4)*roadRoute.get(i+1))/frameHeight);

			}*/
			if (isValid == 0)
			{
				boolean b = true;			// this boolean decides at the end shows if a map is valid. the for loops try to make b false, if b is true after all testing then the map is valid
				int greaterX = 0;
				int greaterY = 0;
				for(int i=0; i<convertedRoute.size();i+=2)
				{

					greaterX=0;
					greaterY=0;
					if (i==0)			// the first entry is the starting point while the second is the end point - this means that the first entry must be compared to the 5th not the third
					{
						greaterX = Math.abs(convertedRoute.get(i)-convertedRoute.get(i+4));
						greaterY = Math.abs(convertedRoute.get(i+1)-convertedRoute.get(i+4+1));

					}
					else if (i==2)		// the endpoint does not need to compare to anything so it is just given true values
					{
						greaterX=1;
						greaterY=0;
					}
					else if (i==convertedRoute.size()-2)	// this is the last entry in the arraylist and must be compared to the exit point value entry
					{
						greaterX = Math.abs(convertedRoute.get(i)-convertedRoute.get(2));
						greaterY = Math.abs(convertedRoute.get(i+1)-convertedRoute.get(3));

					}
					else		// the rest of the values are simply compared to the next value in the list
					{
						greaterX = Math.abs(convertedRoute.get(i)-convertedRoute.get(i+2));
						greaterY = Math.abs(convertedRoute.get(i+1)-convertedRoute.get(i+3));
					}

					if (greaterX+greaterY!=1)		// in order for a move to be valid, it must be 1 step in at most 1 direction
					{
						b=false;
						i=convertedRoute.size();
					}
				}

				if (b == true)
				{
					validity = "This map is valid";	// this prints on screen if map is valid
					System.out.println("ConvertedRoute Size = "+ convertedRoute.size());
					convertMapSquares(convertedRoute);							//*************************WRONG PLACE
					saveMap(convertedRoute,XgridNumber,YgridNumber, "Brennan's Map");
					isValid =1;
				} 
				else
				{
					validity = "This map is invalid";				// this prints on screen if map is invalid
				}
			}

		}



		private void saveMap(ArrayList<Integer> conRoute, int xgridNumber, int ygridNumber, String mapName) 
		{ 
			if (saveMap==0)	//needed due to multiple button presses being caught
			{
				storedMaps.add(convertedRoute);
				try {
					FileWriter fw = new FileWriter("Maps.csv");
					PrintWriter outputFile = new PrintWriter(fw);
					mapNames.add(mapName);
					//for(int i = 0; i<conRoute.size();i++)
					//outputFile.print(XgridNumber+","+YgridNumber +",");
					for (int y = 0; y<storedMaps.size();y++)
					{
						outputFile.print(mapNames.get(y)+",");
						outputFile.print(XgridNumber+","+YgridNumber +",");
						for(Integer a : storedMaps.get(y))
							//for(int i = 0; i<conRoute.size();i++)
						{

							outputFile.print(a+",");
							//	outputFile.print(conRoute.get(i)+",");
						}
						outputFile.println();
					}
					outputFile.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("conroute size: " + conRoute.size());
				/*			System.out.println(XgridNumber+" , "+YgridNumber +",");
			for(int x = 0; x<conRoute.size();x++)
			{
				System.out.print(conRoute.get(x) +",");
			}
			System.out.println("It Should have saved this data");*/
				saveMap=1;
			}
		}

		private void convertMapSquares (ArrayList<Integer> conRoute) 
		{
			if (convertMap==0)	//needed due to multiple button presses being caught
			{
				System.out.println("YGridNumber = " + YgridNumber + " XgridNumber = " + XgridNumber);
				mapSquareLayout = new MapSquare[YgridNumber][XgridNumber];
				for (int i =0; i<conRoute.size(); i+=2)
				{
					int a = conRoute.get(i+1)-1;
					int b = conRoute.get(i)-1;
					System.out.println("conRoute.geti+1 = " + a);
					System.out.println("conRoute.geti = " + b);
					mapSquareLayout[conRoute.get(i+1)-1][conRoute.get(i)-1] = new RoadMapSquare(conRoute.get(i)-1, conRoute.get(i+1)-1);
				}
				for (int i =0; i<conRoute.size(); i+=2)
				{
					if (i ==0)
					{
						RoadMapSquare m = (RoadMapSquare) mapSquareLayout[conRoute.get(i+1)-1][conRoute.get(i)-1];
						m.setNextSquare(mapSquareLayout[conRoute.get(i+5)-1][conRoute.get(i+4)-1]);
					}
					else if (i == conRoute.size()-2)
					{
						RoadMapSquare m = (RoadMapSquare) mapSquareLayout[conRoute.get(i+1)-1][conRoute.get(i)-1];
						m.setNextSquare(mapSquareLayout[conRoute.get(3)-1][conRoute.get(2)-1]);
					}
					else if (i ==2)
					{

					}
					else
					{
						RoadMapSquare m = (RoadMapSquare) mapSquareLayout[conRoute.get(i+1)-1][conRoute.get(i)-1];
						m.setNextSquare(mapSquareLayout[conRoute.get(i+3)-1][conRoute.get(i+2)-1]);
					}
				}
				convertMap=1;
			}
		}
	}
}
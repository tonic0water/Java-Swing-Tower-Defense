package visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import Map.RoadMapList;
import Map.RoadMapSquare;
import critterMain.Position;

public class GameScreen extends JPanel {


	private static final long serialVersionUID = 507547113269203385L;
	private int w;
	private int h;

	private int horSize;
	private int verSize;
	

	protected ArrayList<RoadPanel> road = new ArrayList<RoadPanel>();
	protected RoadMapList roadList = new RoadMapList();
	protected ArrayList<FieldPanel> field = new ArrayList<FieldPanel>(); //field is anything but the road

	public GameScreen(int width, int height, int dimensions){ //TODO: dimensions represent square size
		w = width;
		h = height;
		w = w/dimensions*dimensions;
		h = h/dimensions*dimensions;
		horSize = w/dimensions;
		verSize = h/dimensions;

		paint(dimensions);

	}

	private void paint(int dimensions){

		this.setSize(w, h);
		this.setOpaque(false);
		this.setLayout(new GridLayout(dimensions, dimensions));
		for(int i = 0; i<dimensions; i++){
			for(int j = 0; j<dimensions; j++){
				if(i == dimensions/2){
					RoadPanel r = new RoadPanel(new Position(i*horSize, j*verSize), horSize, verSize);
					road.add(r);
					this.add(r);
				}
				else{
					FieldPanel f = new FieldPanel(new Position(i*horSize, j*verSize), horSize, verSize, Color.WHITE);
					field.add(f);
					this.add(f);
				}
			}
		}
	}
}

/*@Override
public void paintComponent(Graphics gr){
	super.paintComponent(gr);
	paintBackground(gr);
}

private void paintBackground(Graphics gr){
	this.setBackground(Color.WHITE);
	this.setSize(w,h);
	for(int x = 0; x<dimensions; x++){
		for(int y = 0; y<dimensions; y++){
		gr.setColor(Color.BLACK);
		gr.drawRect(x*horSize, y*verSize, horSize, verSize);
		}
	}
}*/

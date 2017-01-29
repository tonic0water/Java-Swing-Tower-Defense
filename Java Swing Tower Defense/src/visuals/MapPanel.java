package visuals;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import critterMain.Position;

public class MapPanel extends JPanel {

	private int w;
	private int h;
	private Position pos;
	private Color c;
	
	public MapPanel(Position p, int width, int height, Color col){
		this.pos = p;
		this.w = width;
		this.h = height;
		this.c = col;
		paintBackground();
	}
	
	private void paintBackground(){
		this.setBackground(c);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}

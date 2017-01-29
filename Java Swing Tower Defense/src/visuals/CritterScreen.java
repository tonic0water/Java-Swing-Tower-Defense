package visuals;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import critterMain.CritterCreator;

public class CritterScreen extends JPanel{
	
	private static final long serialVersionUID = -3414624423350856579L;
	private int w;
	private int h;
	protected CritterCreator critGen;
	

	public CritterScreen(int width, int height, int dimensions, int wave){
		
		w = width;
		h = height;
		w = w/dimensions*dimensions;
		h = h/dimensions*dimensions;
		critGen = new CritterCreator(wave);
		
		paint();
	}

	private void paint(){
		this.setSize(w, h);
		this.setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics gr){
		
		super.paintComponent(gr);
	}
}

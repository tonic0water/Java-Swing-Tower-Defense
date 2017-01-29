package Map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Map.Screen.MouseHeld;

public class MouseHandler implements MouseListener, MouseMotionListener {			// this class takes in and interprets input from user cursor
	
	private Screen screen;
	private MouseHeld mouseHeld;
	
	public MouseHandler (Screen screen)
	{
		this.screen = screen;
		this.mouseHeld = this.screen.new MouseHeld();
	}
	
	public void mouseDragged(MouseEvent e) {			// checks if mouse is moved
		this.mouseHeld.mouseMoved(e);
	}

	
	public void mouseMoved(MouseEvent e) {				// checks if mouse is moved
		this.mouseHeld.mouseMoved(e);
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {			// notices if mouse is held down
		this.mouseHeld.mouseDown(e);
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}

}

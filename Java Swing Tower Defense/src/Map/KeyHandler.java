package Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{			// this class takes in and interprets input from the user keyboard

	private Screen screen;
	private Screen.KeyTyped keyTyped;
	
	public KeyHandler(Screen screen)
	{
		this.screen = screen;
		
		this.keyTyped = this.screen.new KeyTyped();
		
		//this.keyTyped.keyESC();			// used to call keyESC method
		
		//Screen.KeyTyped.key				//Static call to key method
	}
	
	public void keyPressed(KeyEvent e) {			// pressed every time a key is pressed it produces the event e
		//System.out.println(e.getKeyCode());		// key in ASCII is printed to the console
		int keyCode = e.getKeyCode();
		
		if(keyCode == 27)				// 27 is for the escape key
		{
			this.keyTyped.keyESC();
		}
		if(keyCode == 32)				// 27 is for the escape key
		{
			this.keyTyped.keySPACE();
		}
		if(keyCode == 77)				// 27 is for the space bar
		{
			this.keyTyped.keyM();
		}
		if (keyCode == 70)				// 70 is for the f key
		{
			this.keyTyped.keyF();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}

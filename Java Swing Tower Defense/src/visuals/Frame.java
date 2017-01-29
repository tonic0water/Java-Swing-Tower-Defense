package visuals;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private final int width = 900;
	private final int height = 600;

	public Frame(){
		this.setTitle("Star Wars Tower Defense");
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//TODO: this.setIconImage(image);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(new Panel(width, height));
	}
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				Frame ex = new Frame();
				ex.setVisible(true);
			}
		});
	}
}

package observer;

import critterMain.Critter;

public class AliveObserver implements IObserver {

	@Override
	public void update(Critter c) {
		System.out.println("The critter is currently: " + (c.isAlive() ? "Alive" : "Dead") + " (the isAlive observer)");
		
	}


}

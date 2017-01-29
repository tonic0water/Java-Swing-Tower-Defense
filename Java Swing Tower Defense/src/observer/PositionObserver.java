package observer;

import critterMain.Critter;

public class PositionObserver implements IObserver {

	@Override
	public void update(Critter c) {
		System.out.println("the position of the critter is now: " + c.getPos() + " (Position Observer)");

	}

}

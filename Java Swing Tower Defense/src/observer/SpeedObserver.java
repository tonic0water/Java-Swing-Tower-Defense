package observer;

import critterMain.Critter;

public class SpeedObserver implements IObserver {

	@Override
	public void update(Critter c) {
		System.out.println("Speed is now: " + c.getSpeed() + " (Speed Observer)");
		

	}

}

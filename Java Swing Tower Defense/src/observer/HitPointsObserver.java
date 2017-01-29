package observer;

import critterMain.Critter;

public class HitPointsObserver implements IObserver {

	@Override
	public void update(Critter c) {
		System.out.println("The hitpoints are now: " + c.getHitPoints() + " (hitpoints observer)");

	}

}

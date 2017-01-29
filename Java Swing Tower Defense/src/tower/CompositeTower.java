package tower;

import java.util.ArrayList;

import critterMain.Critter;

public class CompositeTower implements ITower {

	private ArrayList<ITower> towers;
	private ArrayList<ITowerObserver> observerList;
	private ITower myReference;
	private static final int cost = 1000;

	public CompositeTower() {
		towers = new ArrayList<>();
		observerList = new ArrayList<>();
	}
	
	public static int getCost() {
		return cost;
	}
	
	public int getValue() {
		int valueTotal = 0;

		for (ITower tower : towers) {
			valueTotal += tower.getValue();
		}
		return valueTotal;
	}

	public void addTower(ITower tower) {
		towers.add(tower);
		this.notifyObservers(this.myReference);
	}

	public void removeTower(ITower tower) {
		towers.remove(tower);
		this.notifyObservers(this.myReference);
	}

	public void myReference(ITower myReference) {
		this.myReference = myReference;
	}
	
	@Override
	public double getDamage() {
		double damageTotal = 0;

		for (ITower tower : towers) {
			damageTotal += tower.getDamage();
		}
		return damageTotal;
	}

	@Override
	public double getRange() {
		double rangeTotal = 0;

		for (ITower tower : towers) {
			rangeTotal += tower.getRange();
		}
		return rangeTotal;
	}

	@Override
	public double getRateOfFire() {
		double rateOfFireTotal = 0;

		for (ITower tower : towers) {
			rateOfFireTotal += tower.getRateOfFire();
		}
		return rateOfFireTotal;
	}

	@Override
	public double getSlowingAbility() {
		double slowingAbilityTotal = 0;

		for (ITower tower : towers) {
			slowingAbilityTotal += tower.getSlowingAbility();
		}
		return slowingAbilityTotal;
	}

	@Override
	public boolean isGroundShootingAbility() {
		boolean isGroundShootingAbility = false;

		for (ITower tower : towers) {
			isGroundShootingAbility = isGroundShootingAbility || tower.isGroundShootingAbility();
		}
		return isGroundShootingAbility;
	}

	@Override
	public boolean isAerialShootingAbility() {
		boolean isAerialShootingAbility = false;

		for (ITower tower : towers) {
			isAerialShootingAbility = isAerialShootingAbility || tower.isAerialShootingAbility();
		}
		return isAerialShootingAbility;
	}

	@Override
	public boolean isIceShootingAbility() {
		boolean isIceShootingAbility = false;

		for (ITower tower : towers) {
			isIceShootingAbility = isIceShootingAbility || tower.isIceShootingAbility();
		}
		return isIceShootingAbility;
	}
	
	public void addTowerObserver(ITowerObserver observer) {
		observerList.add(observer);
	}
	
	public void removeTowerObserver(ITowerObserver observer) {
		observerList.remove(observer);
	}

	public void notifyObservers(ITower myReference) {
		for (ITowerObserver observer: observerList) {
			observer.update(myReference);
		}
	}


}

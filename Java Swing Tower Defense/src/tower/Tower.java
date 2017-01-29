package tower;

import java.util.ArrayList;

import critterMain.Critter;

public abstract class Tower implements ITower {
	private double damage;
	private double range;
	private double rateOfFire;
	private double slowingAbility;
	private int cost;
	private boolean groundShootingAbility;
	private boolean airShootingAbility;
	private boolean iceShootingAbility;
	private ArrayList<ITowerObserver> observerList;
	private ITower myReference;


	public Tower(double damage, double range, double rateOfFire, double slowingAbility, int cost, boolean groundShootingAbility,
			boolean airShootingAbility, boolean iceShootingAbility) {

		this.damage = damage;
		this.range = range;
		this.rateOfFire = rateOfFire;
		this.slowingAbility = slowingAbility;
		this.groundShootingAbility = groundShootingAbility;
		this.airShootingAbility = airShootingAbility;
		this.iceShootingAbility = iceShootingAbility;
		this.cost = cost;
		this.observerList = new ArrayList<>();
		this.myReference = this;
	}

	public double getDamage() {
		return damage;
	}

	public double getRange() {
		return range;
	}

	public double getRateOfFire() {
		return rateOfFire;
	}

	public double getSlowingAbility() {
		return slowingAbility;
	}

	public int getValue() {
		return cost;
	}

	public boolean isGroundShootingAbility() {
		return groundShootingAbility;
	}

	public boolean isAerialShootingAbility() {
		return airShootingAbility;
	}

	public boolean isIceShootingAbility() {
		return iceShootingAbility;
	}

	public void addTowerObserver(ITowerObserver observer) {
		observerList.add(observer);
	}

	public void removeTowerObserver(ITowerObserver observer) {
		observerList.remove(observer);
	}

	// when an upgrade is applied, the latest upgrade reference is passed through to this point, where all the
	// the update() method of all observers of the tower are called. The reference of the applied upgrade is provided
	// as input...
	public void notifyObservers(ITower myReference) {
		for (ITowerObserver observer: observerList) {
			observer.update(myReference);
		}
	}

}


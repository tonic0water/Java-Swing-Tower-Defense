package tower;

import java.util.ArrayList;

import critterMain.Critter;

public abstract class UpgradeDecorator implements ITower {
	protected ITower decoratee;
	private ITower myReference;
	private int cost;
	
	public UpgradeDecorator(ITower tower, int cost) {
		this.decoratee = tower;
		this.cost = cost;
		this.myReference = this;
		this.notifyObservers(this);
	}
	
	public double getDamage() {
		return decoratee.getDamage();
	}
	
	public double getRange() {
		return decoratee.getRange();
	}
	
	public double getRateOfFire() {
		return decoratee.getRateOfFire();
	}
	
	public double getSlowingAbility() {
		return decoratee.getSlowingAbility();
	}
	
	public int getValue() {
		return decoratee.getValue() + cost;
	}

	public boolean isGroundShootingAbility() {
		return decoratee.isGroundShootingAbility();
	}

	public boolean isAerialShootingAbility() {
		return decoratee.isAerialShootingAbility();
	}
	public boolean isIceShootingAbility() {
		return decoratee.isIceShootingAbility();
	}
	// we send the current upgrade reference to the previous decoratee. 
	public void notifyObservers(ITower myReference) {
		decoratee.notifyObservers(myReference);
	}
}

package tower;

public interface ITower{
	public double getDamage();
	public double getRange();
	public double getRateOfFire();
	public double getSlowingAbility();
	public int getValue();
	public boolean isGroundShootingAbility();
	public boolean isAerialShootingAbility();
	public boolean isIceShootingAbility();
	public void notifyObservers(ITower myReference);
}

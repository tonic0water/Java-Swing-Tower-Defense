package tower;

public class AerialTower extends Tower {
	private static final double damage = 10.0;
	private static final double range = 1.0;
	private static final double rateOfFire = 1;
	private static final double slowingAbility = 0;
	private static final int cost = 100;
	private static final boolean groundShootingAbility = false;
	private static final boolean aerialShootingAbility = true;
	private static final boolean iceShootingAbility = false;
	
	public AerialTower() {
		super(damage, range, rateOfFire, slowingAbility, cost, groundShootingAbility, aerialShootingAbility, iceShootingAbility);
	}
	
	public static int getCost() {
		return cost;
	}
}

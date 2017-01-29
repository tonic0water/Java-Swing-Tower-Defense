package tower;

import java.util.ArrayList;

import critterMain.Critter;

public class DamageUpgrade extends UpgradeDecorator {
	private double damageMultiplyer = 1.2;
	private static final int cost = 30;

	public DamageUpgrade(ITower tower) {
		super(tower, cost);
	}
	
	@Override
	public double getDamage() {
		return decoratee.getDamage()*damageMultiplyer;
	}

	
}

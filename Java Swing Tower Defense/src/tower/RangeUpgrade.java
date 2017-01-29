package tower;

import java.util.ArrayList;

import critterMain.Critter;

public class RangeUpgrade extends UpgradeDecorator {
	private double rangeMultiplyer = 2.0;
	private static final int cost = 20;

	public RangeUpgrade(ITower tower) {
		super(tower, cost);
	}
	
	@Override
	public double getRange() {
		return decoratee.getRange()*rangeMultiplyer;
	}
	
}

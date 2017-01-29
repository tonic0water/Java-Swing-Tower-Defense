package tower;

public class UpgradeFactory {

	private static UpgradeFactory uniqueInstance;
	// singleton...
	private UpgradeFactory() {
		
	}

	public static UpgradeFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new UpgradeFactory();
		}
		return uniqueInstance;
	}

	public ITower newUpgrade(String type, ITower tower) {
		ITower t;
		// calling myReference(ITower) sets the myReference variable in every upgrade to its own reference.
		switch (type.toLowerCase()) {
		case "damage":
			t = new DamageUpgrade(tower);
			return t;
		case "range":
			t = new RangeUpgrade(tower);
			return t;
		case "rateoffire":
			t = new RateOfFireUpgrade(tower);
			return t;
		default:
			return null;
		}
	}
}

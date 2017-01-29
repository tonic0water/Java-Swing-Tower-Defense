/*package tower;

import user.Player;

public class TowerFactory {

	private static TowerFactory uniqueInstance;
	private Player player;

	private TowerFactory() {
		player = Player.getUniqueInstance();
	}

	public static TowerFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TowerFactory();
		}
		return uniqueInstance;
	}

	public ITower newTower(String type) {
		ITower t;

		// calling myReference(ITower) sets the myReference variable in every tower to its own reference.
		switch (type.toLowerCase()) {
		case "aerialtower":
			t = new AerialTower();
			break;
		case "groundtower":
			t = new GroundTower();
			break;
		case "icetower":
			t = new IceTower();
			break;
		case "compositetower":
			t = new CompositeTower();
			break;
		default:
			t = null;
			break;
		}
		if (t != null && player.getMoney() >= t.getValue()) {
			player.setMoney(player.getMoney() - t.getValue());
			return (ITower) t;
		}
		else {
			return null;
		}
	}

}
*/
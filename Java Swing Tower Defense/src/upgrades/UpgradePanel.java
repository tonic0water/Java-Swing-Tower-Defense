package upgrades;

import java.awt.GridLayout;

import javax.swing.JPanel;

import tower.AerialTower;
import tower.GroundTower;
import tower.ITower;
import tower.IceTower;

public class UpgradePanel extends JPanel{
	
	ITower towerG = new GroundTower();
	ITower towerI = new IceTower();
	ITower towerA = new AerialTower();

	public UpgradePanel(){
		
		super();
		setupPanel();
	}
	
	private void setupPanel(){
		
		this.setLayout(new GridLayout(1,3));
	}
	
	//private JPanel subPanel(int level){
		
		
	}


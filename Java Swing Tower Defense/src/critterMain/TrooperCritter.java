package critterMain;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrooperCritter extends Critter{

	/**
	 * The constructor for a critter of type Trooper which sets it's resistances and speed 
	 * also inherits from it's superclass Critter
	 * @param level
	 */
	public TrooperCritter(int level){
		super(level);

		editHP();
		this.resist.add("water");
		this.resist.add("laser");
		this.type = "Trooper";
		try {
			this.sprite = ImageIO.read(new File("src/critterMain/critter.png"));
		} catch (IOException e) {
			System.out.println("File does not exist!");
			e.printStackTrace();
		}
	}
	private void editHP(){
		this.hitPoints *= 1.5;
	}


}

package critterMain;

public class DroidCritter extends Critter{
	/**
	 * The constructor for a critter of type Droid which sets it's resistances and speed 
	 * also inherits from it's superclass Critter
	 * @param level
	 */
	public DroidCritter(int level){
		super(level);
		editSpeed();
		editHP();
		this.resist.add("fire");
		this.resist.add("laser");
		this.type = "Droid";
	}
	private void editSpeed(){
		this.speed *= 0.8;
	}
	private void editHP(){
		this.hitPoints *= 1.8;
	}
	
	
}

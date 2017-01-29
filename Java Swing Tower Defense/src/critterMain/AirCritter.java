package critterMain;

public class AirCritter extends Critter{
	/**
	 * The constructor for a critter of type Air which sets it's resistances and speed 
	 * also inherits from it's superclass Critter
	 * @param level
	 */
	public AirCritter(int level){
		super(level);
		editSpeed();
		editHP();
		this.resist.add("fire");
		this.resist.add("water");
		this.type = "Air";
	}
	private void editSpeed(){
		this.speed *= 1.2;
	}
	private void editHP(){
		this.hitPoints *= 1.3;
	}
	
	
}

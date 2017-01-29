package critterMain;

//import playerMain.Player;
import critterMain.*;

public class Game {

	/**public static void main(String[] args){
		runWave(3);
	} **/
	/*The way I have set up my runWave method is so that it first creates a Player Object in order to test if the critter can 
	 * take away its life points or add money based on the outcome of the wave. Secondly it generates a list of Critter 
	 * objects of type either Air, Trooper, or Droid and their hit points and speed are dependent on the wave number. Then,
	 * in order to test that these Critter object can be dealt damage relative to their resistances, the takesDamage method is called 
	 * In order to check whether or not money should be awarded, two boolean variables are created before and after the takesDamage
	 * method is called which are equal to the isAlive property of each critter. If the Critter is alive before it takes damage and not 
	 * afterwafrds, then money is awarded based on its hitpoints. If is is not alive before or after, then system knows that 
	 * money has already been awarded for that particular critter. 
	 * Each critter is dealt damage only once and damage is incremented by 20 just to see the outcome of different damages 
	 * given to each critter and how the player object changes.
	*/
	public static void runWave(int wave){
		Player player1 = new Player();
		CritterCreator critters = new CritterCreator(wave);
		System.out.println(player1);
		for(int i = 0; i <  critters.getNumOfCritters() ;i++){
			boolean aliveBeforeDamage = critters.getCritter(i).isAlive();
			System.out.println("Critter number: " + (i+1) + " " + critters.getCritter(i));
			int initialLife = critters.getCritter(i).getHitPoints();
			critters.getCritter(i).takesDamage(35+i*20, "fire");
			boolean aliveAfterDamage = critters.getCritter(i).isAlive();
			System.out.println("Amount of fire damage done without resistance is: " + (35+i*20));
			System.out.println("Amount of fire damage done with resistance is: " + (initialLife - critters.getCritter(i).getHitPoints()));
			System.out.println("Critter number: " + (i+1) + " " + critters.getCritter(i));
			if(aliveBeforeDamage && !aliveAfterDamage){
				player1.addMoney(initialLife);
			}
			else{
				player1.subtractLife(1);
			}
			System.out.println(player1 + "\n");
		}
		
	}
}

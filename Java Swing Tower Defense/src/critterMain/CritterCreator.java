package critterMain;

import java.util.ArrayList;

public class CritterCreator {

	private ArrayList<Critter> listOfCritters = new ArrayList<Critter>();
	
	public CritterCreator(int wave){
		generateCritters(wave);
		
	}
	
	/**
	 * Populates the ArrayList of Critters with the three different types of Critter objects
	 * @param wave
	 */
	private void generateCritters(int wave){
		for(int i = 0; i < numberOfCritters(wave)/3; i ++){
			listOfCritters.add(new DroidCritter(critterLevel(wave)));
		}
		for(int i = 0; i < numberOfCritters(wave)/3; i ++){
			listOfCritters.add(new AirCritter(critterLevel(wave)));
		}
		for(int i = 0; i < numberOfCritters(wave)/3; i ++){
			listOfCritters.add(new TrooperCritter(critterLevel(wave)));
		}
	}
	
	/**
	 * Determines how many Critter objects are created per wave, using a looping 9,15,21 pattern
	 * @param wave
	 * @return An integer representing the number of critters created in a specific wave
	 */
	private int numberOfCritters(int wave){
		return 9 + (wave%3)*6;
	}
	/**
	 * Gradually increases Critter object's level based on the wave which in turn increases
	 * Critter hit points
	 * @param wave
	 * @return An integer representing the Critter Objects level, necessary for the constructor
	 */
	private int critterLevel(int wave){
		return wave/3 + 1;
	}
	 
	//Helper methods to status of Critters during a wave
	
	public Critter getCritter(int position){
		return this.listOfCritters.get(position);
	}
	
	public int getNumOfCritters(){
		return this.listOfCritters.size();
	}
	
	public String toString(){
		String name = "";
		for(Critter a : listOfCritters){
			name += a + " \n";
		}
		return name;
	}
	
}

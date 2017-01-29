package critterMain;

//This class will interact with the Map class in order to specify exactly where the critter can move
public class Position {
	
	private double xPos; //bounded by MAX_X_VAL
	private double yPos; //bounded by MAX_Y_VAL
	
	public Position(double xIn, double yIn){
		
		this.xPos = xIn;
		this.yPos = yIn;
	}

	public double getxPos(){
		return this.xPos;
	}
	
	public double getyPos(){
		return this.yPos;
	}
}

package playerMain;

public class Player {

	private int currency;
	private int lifePoints;
	private static Player uniqueInstance;

	public Player(){
		this.currency = 1000;
		this.lifePoints = 1000;
	}

	/***************************
	 *		   SETTERS		   *
	 ***************************/

	public void addCurrency(int wave){
		this.currency += wave * 10;
	}

	public void subCurrency(int sub){
		if (this.currency - sub < 0){
			System.out.println("You don't have enough money for that, BITCH");
			return;
		}
		this.currency -= sub;
	}

	public void subtractLP(int sub){
		this.lifePoints -= sub;
		if (this.lifePoints <= 0){
			System.out.println("You died, BITCH");
			System.exit(0);
		}
	}

	/***************************
	 *		   GETTERS		   *
	 ***************************/

	public int getCurrency(){
		return this.currency;
	}

	public int getLP(){
		return this.lifePoints;
	}
	
	public static Player getUniqueInstance()
	{
		if (uniqueInstance==null)
		{
			uniqueInstance = new Player();
		}

		return uniqueInstance;
		
	}

	public String toString(){
		return "Player has $" + this.currency + " and " + this.lifePoints + " life points";
	}

}

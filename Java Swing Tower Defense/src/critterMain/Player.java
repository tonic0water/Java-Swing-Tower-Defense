package critterMain;

public class Player {
	private int lifePoints;
	private int money;

	public Player(){
		this.lifePoints = 20;
		this.money = 500;
	}

	public void addMoney(int moneyGained){
		this.money += moneyGained;
	}

	public void subtractMoney(int moneySpent){
		if (this.money - moneySpent < 0){
			System.out.println("Not enough funds");
			return;
		}
		this.money -= moneySpent;

	}
	
	public void subtractLife(int painTaken){
		this.lifePoints -= painTaken;
		if (this.lifePoints - painTaken <= 0){
			System.out.println("GAME OVER");
			System.exit(0);
		}
	}
	
	public int getHealthPoints(){
		return this.lifePoints;
	}
	public int getMoney(){
		return this.money;
	}
	public String toString(){
		return "Player's life points: " + this.lifePoints + "; Player's money: " + this.money;
	}
}

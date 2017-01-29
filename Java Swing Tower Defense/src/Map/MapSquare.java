package Map;

public class MapSquare {
	private int Xposition;
	private int Yposition;
	
	protected MapSquare(int Xpos, int Ypos)
	{
		this.Xposition = Xpos;
		this.Yposition = Ypos;
	}

	public int getXposition() {
		return Xposition;
	}

	public void setXposition(int xposition) {
		Xposition = xposition;
	}

	public int getYposition() {
		return Yposition;
	}

	public void setYposition(int yposition) {
		Yposition = yposition;
	}

	

}
